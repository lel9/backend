package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import testsystem.dto.TaskDescriptionDTO;
import testsystem.dto.TaskNewDTO;
import testsystem.service.TaskServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDescriptionDTO getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @PostMapping(value = "/task/add", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@Valid TaskNewDTO taskNewDTO,
                        @RequestParam("tests") @Valid @NotNull(message = "Файл с тестами должен быть задан") MultipartFile file) {
        taskService.addTask(taskNewDTO, file);
    }
}
