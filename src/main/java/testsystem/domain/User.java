package testsystem.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

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

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password_hash;

    @Enumerated(EnumType.STRING)
    private final UserRole role = UserRole.USER;

}
