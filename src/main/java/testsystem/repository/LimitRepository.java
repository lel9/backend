package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.Limit;

import java.util.UUID;

public interface LimitRepository extends JpaRepository<Limit, UUID> {
}
