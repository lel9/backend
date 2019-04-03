package testsystem.service;

import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;

public interface CategoryService {

    Category addNewCategory(CategoryDTO categoryDTO);

    CategoryListDTO getCategoriesList();

    Category editCategory(CategoryDTO categoryDTO);

    void deleteCategory(CategoryDTO categoryDTO);
}
