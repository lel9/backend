package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import testsystem.domain.Category;
import testsystem.domain.Task;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.TaskDTO;
import testsystem.dto.TaskListDTO;
import testsystem.exception.CategoryAlreadyExistsException;
import testsystem.exception.NoSuchCategoryException;
import testsystem.repository.CategoryRepository;
import testsystem.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Category addNewCategory(CategoryDTO categoryDTO) {
        Category category = Category.fromCategoryDTO(categoryDTO);
        validateCategoryNameExists(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public CategoryListDTO getCategoriesList() {
        List<Category> all = categoryRepository.findAll();

        List<CategoryDTO> list = new ArrayList<>();

        all.forEach(category -> list.add(
                new CategoryDTO(
                        category.getId().toString(),
                        category.getName(),
                        category.getTasks().size()
                )
        ));

        return new CategoryListDTO(list);

    }

    @Override
    public TaskListDTO getTasksList(String id, int page, int limit) {
        UUID uuid = validateId(id);
        Category category = validateCategoryExists(uuid);

        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "name"));
        Page<Task> tasks = taskRepository.findByCategory(category, pageableRequest);

        List<TaskDTO> list = new ArrayList<>();
        tasks.forEach(task -> list.add(
                new TaskDTO(task.getId().toString(), task.getName())
        ));

        return new TaskListDTO(tasks.getTotalPages(), category.getName(), list);
    }

    @Override
    public Category editCategory(CategoryDTO categoryDTO) {
        UUID uuid = validateId(categoryDTO.getId());
        Category category = validateCategoryExists(uuid);
        if (!category.getName().equals(categoryDTO.getName()))
            validateCategoryNameExists(categoryDTO.getName());

        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) {
        UUID uuid = validateId(categoryDTO.getId());
        Category category = validateCategoryExists(uuid);
        categoryRepository.delete(category);
    }

    private UUID validateId(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            throw new NoSuchCategoryException();
        }
        return uuid;
    }

    private Category validateCategoryExists(UUID id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent())
            throw new NoSuchCategoryException();
        return categoryOptional.get();
    }

    private void validateCategoryNameExists(String name) {
        if (categoryRepository.findByName(name) != null) {
            throw new CategoryAlreadyExistsException(name);
        }
    }
}
