package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.CategoryView;
import testsystem.dto.TaskListDTO;
import testsystem.service.CategoryServiceImpl;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/category/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public void addNewCategory(@RequestBody @Validated(CategoryView.ADD.class) CategoryDTO categoryDTO) {
        categoryService.addNewCategory(categoryDTO);
    }

    @PostMapping("/category/edit")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void editCategory(@RequestBody @Validated(CategoryView.EDIT.class) CategoryDTO categoryDTO) {
        categoryService.editCategory(categoryDTO);
    }

    @PostMapping("/category/delete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void deleteCategory(@RequestBody @Validated(CategoryView.DELETE.class) CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
    }

    @GetMapping("/category/list")
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategoryList() {
        return categoryService.getCategoriesList();
    }

    @GetMapping("/categories/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDTO getTaskList(@PathVariable String id) {
        return categoryService.getTasksList(id);
    }
}
