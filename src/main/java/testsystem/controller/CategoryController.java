package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.service.TaskServiceImpl;

import javax.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/category/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        taskService.addNewCategory(Category.fromCategoryDTO(categoryDTO));
    }
}
