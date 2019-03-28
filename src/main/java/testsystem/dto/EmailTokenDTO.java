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
public class EmailTokenDTO {

    @NotNull(message = "Токен верификации должен быть задан")
    @NotEmpty(message = "Токен верификации не должен быть пуст")
    private String token;

}
