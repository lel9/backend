package testsystem.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import testsystem.domain.*;
import testsystem.dto.*;
import testsystem.exception.NoSuchTaskException;
import testsystem.exception.TaskAlreadyExistsException;
import testsystem.exception.zip.*;
import testsystem.repository.LimitRepository;
import testsystem.repository.TaskRepository;
import testsystem.repository.TestRepository;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public TaskListDTO getTasksList(String id, int page, int limit, boolean categorized) {
        Category category = null;

        if (categorized) {
            UUID uuid = validateId(id);
            category = categoryService.validateCategoryExists(uuid);
        }

        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "name"));
        Page<Task> tasks = taskRepository.findByCategory(category, pageableRequest);

        List<TaskDTO> list = new ArrayList<>();
        tasks.forEach(task -> list.add(
                new TaskDTO(task.getId().toString(), task.getName())
        ));

        return new TaskListDTO(tasks.getTotalPages(), category != null ? category.getName() : null, list);
    }

    @Override
    public TaskDescriptionDTO getTask(String id) {
        UUID uuid = validateId(id);
        Task task = validateTaskExists(uuid);

        String name = task.getName();
        String description = task.getDescription();
        String access = task.getReport_permission();
        TaskCategoryDTO category = getCategoryDTO(task);
        List<LanguageDTO> languages = getTotalLanguages();
        List<LimitDTO> limits = getLimitsDTO(task);

        return new TaskDescriptionDTO(name, description, access, category, languages, limits);
    }

    @Override
    public Task addTask(TaskNewDTO taskDTO, MultipartFile file) {
        String name = taskDTO.getName();
        String description = taskDTO.getDescription();
        String access = taskDTO.getAccess_report();

        UUID uuid = categoryService.validateId(taskDTO.getCategory());
        Category category = categoryService.validateCategoryExists(uuid);

        validateTaskNameExists(name, category);

        Task task = new Task(name, description, access, category);

        List<Test> tests = unzipFileAndGetTests(file);
        Task saved = taskRepository.save(task);

        tests.forEach(test -> {
            test.setTask(saved);
            testRepository.save(test);
        });

        Limit limitC = new Limit(
                taskDTO.getMemory_limit_c(), taskDTO.getTime_limit_c(), saved, ProgrammingLanguage.c
        );
        Limit limitCpp = new Limit(
                taskDTO.getMemory_limit_cpp(), taskDTO.getTime_limit_cpp(), saved, ProgrammingLanguage.cpp
        );
        Limit limitPython = new Limit(
                taskDTO.getMemory_limit_python(), taskDTO.getTime_limit_python(), saved, ProgrammingLanguage.python
        );

        limitRepository.save(limitC);
        limitRepository.save(limitCpp);
        limitRepository.save(limitPython);

        return taskRepository.findById(saved.getId()).get();
    }

    private List<Test> unzipFileAndGetTests(MultipartFile multipartFile) {
        File file = null;
        List<Test> testsFromFile;
        try {
            file = getFileFromMultipartFile(multipartFile);
            testsFromFile = getTestsFromFile(file);
        } catch (IOException e) {
            throw new TestFileIOException();
        } finally {
            if (file != null && file.getName().endsWith("tmp"))
                file.delete();
        }
        return testsFromFile;
    }

    private List<Test> getTestsFromFile(File file) throws IOException {
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(file);
        } catch (IOException e) {
            throw new OpenFileException();
        }

        Map<String, Test> map = new HashMap<>();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                map.put(entry.getName().substring(0, entry.getName().lastIndexOf("/")), new Test());
            } else {
                String[] dirNameVsDataName = entry.getName().split("/");
                if (dirNameVsDataName.length < 2)
                    throw new InvalidFileInZip();
                if (dirNameVsDataName.length > 2)
                    throw new InvalidFileInDirectory(dirNameVsDataName[0]);
                if (!dirNameVsDataName[1].matches("in.txt|out.txt"))
                    throw new InvalidFileInDirectory(dirNameVsDataName[0]);

                String data = readFile(zipFile, entry);

                if (dirNameVsDataName[1].equals("in.txt"))
                    map.get(dirNameVsDataName[0]).setInput_data(data);
                else
                    map.get(dirNameVsDataName[0]).setOutput_data(data);
            }
        }

        map.forEach((key, test) -> {
            if (test.getInput_data() == null)
                throw new NoInputData(key);
            if (test.getOutput_data() == null)
                throw new NoOutputData(key);
        });

        if (map.isEmpty())
            throw new NoOneTestException();

        return new ArrayList<>(map.values());
    }

    private UUID validateId(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            throw new NoSuchTaskException();
        }
        return uuid;
    }

    private Task validateTaskExists(UUID id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent())
            throw new NoSuchTaskException();
        return taskOptional.get();
    }

    private void validateTaskNameExists(String name, Category category) {
        if (taskRepository.findByCategory(category).stream().anyMatch(task -> task.getName().equals(name)))
            throw new TaskAlreadyExistsException(name, category.getName());
    }

    private TaskCategoryDTO getCategoryDTO(Task task) {
        TaskCategoryDTO categoryDTO = null;
        Category category = task.getCategory();
        if (category != null)
            categoryDTO = new TaskCategoryDTO(category.getId().toString(), category.getName());
        return categoryDTO;
    }

    private List<LimitDTO> getLimitsDTO(Task task) {
        List<LimitDTO> limits = new ArrayList<>();
        if (task.getLimits() != null) {
            task.getLimits().forEach(limit -> {
                limits.add(
                        new LimitDTO(limit.getProgramming_language().toString(), limit.getMemory_limit(), limit.getTime_limit())
                );
            });
        }
        return limits;
    }

    private List<LanguageDTO> getTotalLanguages() {
        List<LanguageDTO> languages = new ArrayList<>();
        for (ProgrammingLanguage value : ProgrammingLanguage.values())
            languages.add(new LanguageDTO(value.toString()));
        return languages;
    }

    private File getFileFromMultipartFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString(), "tmp");
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(multipartFile.getInputStream(), outputStream);
        outputStream.close();

        return file;
    }

    private String readFile(ZipFile zipFile, ZipEntry entry) throws IOException {

        InputStream is = zipFile.getInputStream(entry);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
