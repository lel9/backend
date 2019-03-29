package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import testsystem.dto.TaskDescriptionDTO;
import testsystem.service.TaskServiceImpl;

@RestController
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDescriptionDTO getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }
}
