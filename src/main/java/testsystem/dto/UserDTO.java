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

    public UserDTO(String username, String email, String role, String accessToken) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.tokens.access_token = accessToken;
    }

    public UserDTO(String username, String role, String accessToken) {
        this.username = username;
        this.role = role;
        this.tokens.access_token = accessToken;
    }

    @NotNull
    @NotEmpty
    @JsonView({View.REST.class, View.UI.class})
    private String username;

    @NotNull
    @NotEmpty
    @Email
    @JsonView({View.REST.class})
    private String email;

    @NotNull
    @NotEmpty
    @JsonView({View.REST.class})
    private String password;

    @JsonView({View.UI.class})
    private String role;

    @JsonView({View.UI.class})
    private Tokens tokens = new Tokens();

    @Data
    private class Tokens {
        @JsonView({View.UI.class})
        private String access_token;
    }

}
