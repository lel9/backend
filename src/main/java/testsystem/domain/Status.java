package testsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "status")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Status {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    private String result = "Ожидает проверки";

    @OneToOne(targetEntity = Test.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "error_test_id")
    private Test error_test;

    private String extended_information; // todo json mapper

}
