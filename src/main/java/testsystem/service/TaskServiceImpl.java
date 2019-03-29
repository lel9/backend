package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testsystem.domain.Category;
import testsystem.domain.ProgrammingLanguage;
import testsystem.domain.Task;
import testsystem.dto.LanguageDTO;
import testsystem.dto.LimitDTO;
import testsystem.dto.TaskCategoryDTO;
import testsystem.dto.TaskDescriptionDTO;
import testsystem.exception.NoSuchTaskException;
import testsystem.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

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

    private TaskCategoryDTO getCategoryDTO(Task task) {
        TaskCategoryDTO categoryDTO = null;
        Category category = task.getCategory();
        if (category != null)
            categoryDTO = new TaskCategoryDTO(category.getId().toString(), category.getName());
        return categoryDTO;
    }

    private List<LimitDTO> getLimitsDTO(Task task) {
        List<LimitDTO> limits = new ArrayList<>();
        task.getLimits().forEach(limit -> {
            limits.add(
                    new LimitDTO(limit.getProgramming_language().toString(), limit.getMemory_limit(), limit.getTime_limit())
            );
        });
        return limits;
    }

    private List<LanguageDTO> getTotalLanguages() {
        List<LanguageDTO> languages = new ArrayList<>();
        for (ProgrammingLanguage value : ProgrammingLanguage.values())
            languages.add(new LanguageDTO(value.toString()));
        return languages;
    }
}
