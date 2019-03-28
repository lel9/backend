package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.exception.CategoryAlreadyExistsException;
import testsystem.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addNewCategory(CategoryDTO categoryDTO) {
        Category category = Category.fromCategoryDTO(categoryDTO);
        if (categoryRepository.findByName(category.getName()) != null) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        categoryRepository.save(category);
    }
}
