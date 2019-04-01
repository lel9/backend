package testsystem.service;

import org.springframework.web.multipart.MultipartFile;
import testsystem.domain.Task;
import testsystem.dto.TaskDescriptionDTO;
import testsystem.dto.TaskNewDTO;

public interface TaskService {

    TaskDescriptionDTO getTask(String id);

    Task addTask(TaskNewDTO taskDTO, MultipartFile file);
}
