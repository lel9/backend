package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import testsystem.dto.UserDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {

    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String username;

    private String email;

    private String password_hash;

    @Enumerated(EnumType.STRING)
    private final UserRole role = UserRole.USER;

    public static User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
    }

}
