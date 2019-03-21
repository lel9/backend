package testsystem.dto;

import lombok.*;
import testsystem.validator.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@PasswordMatches
public class UserDTO {

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

}
