package testsystem.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class UserDTO {

    public UserDTO(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
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

    private String role;

}
