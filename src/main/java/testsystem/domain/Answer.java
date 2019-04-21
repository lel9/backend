package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answers")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Answer {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String program_text;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programming_language;
}
