package testsystem.service;

import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;

public interface CategoryService {
    void addNewCategory(CategoryDTO categoryDTO);

    CategoryListDTO getCategoriesList();
}
