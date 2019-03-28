package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void addNewCategory(CategoryDTO categoryDTO) {
        Category category = Category.fromCategoryDTO(categoryDTO);
        validateCategoryNameExists(category.getName());
        categoryRepository.save(category);
    }

    @Override
    public CategoryListDTO getCategoriesList() {
        List<Category> all = categoryRepository.findAll();

        List<CategoryDTO> list = new ArrayList<>();

        all.forEach(category -> list.add(
                new CategoryDTO(
                    category.getId().toString(),
                    category.getName(),
                    taskRepository.findByCategory(category).size()
                )
        ));

        return new CategoryListDTO(list);

    }

    @Override
    public TaskListDTO getTasksList(String id) {
        UUID uuid = validateId(id);
        Category category = validateCategoryExists(uuid);
        List<Task> tasks = taskRepository.findByCategory(category);

        List<TaskDTO> list = new ArrayList<>();
        tasks.forEach(task -> list.add(
                new TaskDTO(task.getId().toString(), task.getName())
        ));

        return new TaskListDTO(list);
    }

    @Override
    public void editCategory(CategoryDTO categoryDTO) {
        UUID uuid = validateId(categoryDTO.getId());
        Category category = validateCategoryExists(uuid);
        validateCategoryNameExists(categoryDTO.getName());

        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
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
