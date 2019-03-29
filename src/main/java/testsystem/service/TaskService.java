package testsystem.service;

import testsystem.dto.TaskDescriptionDTO;

public interface TaskService {

    TaskDescriptionDTO getTask(String id);
}
