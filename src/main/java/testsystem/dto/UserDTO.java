package testsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO implements IResultResponse {

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
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    private String role;

    private Tokens tokens = new Tokens();

    @Data
    private class Tokens {
        private String access_token;
    }

}
