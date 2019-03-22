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

    public static UserDTO userWithoutEmailAndWithToken(String username,
                                                       String role,
                                                       String token) {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setRole(role);
        Tokens tokens = new Tokens();
        tokens.setAccess_token(token);
        user.setTokens(tokens);
        return user;
    }

    public static UserDTO userWithoutEmailAndWithoutToken(String username,
                                                          String role) {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setRole(role);
        return user;
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

    private Tokens tokens = new Tokens();

    @Data
    private static class Tokens {
        @JsonView({View.UI.class})
        private String access_token;
    }

}
