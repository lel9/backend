package testsystem.domain;

import javax.persistence.*;

import lombok.*;
import testsystem.dto.UserDTO;

import java.util.UUID;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
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
