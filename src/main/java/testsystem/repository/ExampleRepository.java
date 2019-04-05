package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.Example;

import java.util.UUID;

public interface ExampleRepository extends JpaRepository<Example, UUID> {
}
