package testsystem.dto;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({View.REST.class, View.UI_AUTH.class, View.UI_REG.class})
    private String username;

    @NotNull
    @NotEmpty
    @Email
    @JsonView(View.UI_REG.class)
    private String email;

    @NotNull
    @NotEmpty
    @JsonView({View.REST.class})
    private String password;

    @JsonView({View.UI_AUTH.class, View.UI_REG.class})
    private String role;

}
