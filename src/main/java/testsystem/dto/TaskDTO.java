package testsystem.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TaskDTO {
    @NotNull(message = "Идентификатор задачи должен быть задан")
    @NotEmpty(message = "Идентификатор задачи не должен быть пуст")
    private String id;

    private String name;
}
