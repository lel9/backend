package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testsystem.dto.CategoryDTO;
import testsystem.dto.CategoryGroup;
import testsystem.dto.CategoryListDTO;
import testsystem.dto.TaskListDTO;
import testsystem.service.CategoryServiceImpl;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/category/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public void addNewCategory(@RequestBody @Validated(CategoryGroup.ADD.class) CategoryDTO categoryDTO) {
        categoryService.addNewCategory(categoryDTO);
    }

    @PostMapping("/category/edit")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void editCategory(@RequestBody @Validated(CategoryGroup.EDIT.class) CategoryDTO categoryDTO) {
        categoryService.editCategory(categoryDTO);
    }

    @PostMapping("/category/delete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void deleteCategory(@RequestBody @Validated(CategoryGroup.DELETE.class) CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
    }

    @GetMapping("/category/list")
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategoryList() {
        return categoryService.getCategoriesList();
    }

    @GetMapping("/categories/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDTO getTaskList(@PathVariable String id,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return categoryService.getTasksList(id, page, limit);
    }
}