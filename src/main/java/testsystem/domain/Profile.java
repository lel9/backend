package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Profile {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private SexType sex;

    private Long birthday;

}
