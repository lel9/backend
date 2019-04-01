package testsystem.service;

import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.TaskListDTO;

public interface CategoryService {

    Category addNewCategory(CategoryDTO categoryDTO);

    CategoryListDTO getCategoriesList();

    TaskListDTO getTasksList(String id, int page, int size);

    Category editCategory(CategoryDTO categoryDTO);

    void deleteCategory(CategoryDTO categoryDTO);
}
