package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testsystem.dto.UserDTO;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
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

    @OneToOne(targetEntity = Profile.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSolution> solutions;

    public User(String username, String email, String password_hash) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }

    public static User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
    }

}
