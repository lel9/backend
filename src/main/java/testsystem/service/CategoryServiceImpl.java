package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.exception.CategoryAlreadyExistsException;
import testsystem.repository.CategoryRepository;
import testsystem.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addNewCategory(CategoryDTO categoryDTO) {
        Category category = Category.fromCategoryDTO(categoryDTO);
        if (categoryRepository.findByName(category.getName()) != null) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        categoryRepository.save(category);
    }

    @Override
    public CategoryListDTO getCategoriesList() {
        List<Category> all = categoryRepository.findAll();

        List<CategoryDTO> list = new ArrayList<>();

        all.forEach(category -> {
            list.add(new CategoryDTO(
                    category.getId().toString(),
                    category.getName(),
                    taskRepository.findByCategory(category).size()));
        });

        return new CategoryListDTO(list);

    }
}
