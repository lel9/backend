package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "task_limits")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Limit {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    private Integer memory_limit;

    private Integer time_limit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programming_language;

}
