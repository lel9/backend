package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import testsystem.dto.UserDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class User {

    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String username;

    private String email;

    private String password_hash;

    private boolean enabled = false;

    @Enumerated(EnumType.STRING)
    private final UserRole role = UserRole.user;

    @OneToOne(targetEntity = Profile.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "profile_id")
    private Profile profile;
<<<<<<< HEAD

    private User(String username, String email, String password_hash) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }
=======
>>>>>>> 1e9637c... EJ-41: add Profile, fix User domains

    private User(String username, String email, String password_hash) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }

    public static User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
    }

}
