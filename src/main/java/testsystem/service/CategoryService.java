package testsystem.service;

import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.TaskListDTO;

public interface CategoryService {
    void addNewCategory(CategoryDTO categoryDTO);

    CategoryListDTO getCategoriesList();

    TaskListDTO getTasksList(String id);

    void editCategory(CategoryDTO categoryDTO);
}
