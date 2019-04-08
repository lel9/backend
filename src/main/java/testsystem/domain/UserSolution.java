package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_solutions")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserSolution {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private Long solution_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "task_id", referencedColumnName = "id")
    private Task task;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "answer_id", referencedColumnName = "id")
    private Answer answer;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "status_id", referencedColumnName = "id")
    private Status status;
}
