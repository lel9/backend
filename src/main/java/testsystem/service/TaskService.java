package testsystem.service;

import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;

import java.util.List;

public interface TaskService {
    void addNewCategory(Category category);

    List<CategoryDTO> getCategoriesList();
}
