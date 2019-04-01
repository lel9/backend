package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tests")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Test {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String input_data;

    private String output_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

}
