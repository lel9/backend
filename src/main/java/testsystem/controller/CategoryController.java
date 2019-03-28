package testsystem.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.CategoryView;
import testsystem.dto.TaskListDTO;
import testsystem.service.CategoryServiceImpl;

import javax.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/category/add")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(CategoryView.ADD.class)
    public void addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.addNewCategory(categoryDTO);
    }

    @PostMapping("/category/edit")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(CategoryView.EDIT.class)
    public void editCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.editCategory(categoryDTO);
    }

    @PostMapping("/category/delete")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(CategoryView.DELETE.class)
    public void deleteCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
    }

    @GetMapping("/category/list")
    @JsonView(CategoryView.LIST.class)
    public CategoryListDTO getCategoryList() {
        return categoryService.getCategoriesList();
    }

    @GetMapping("/categories/{id}/tasks")
    public TaskListDTO getTaskList(@PathVariable String id) {
        return categoryService.getTasksList(id);
    }
}
