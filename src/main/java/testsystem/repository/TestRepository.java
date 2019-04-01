package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.Test;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
}
