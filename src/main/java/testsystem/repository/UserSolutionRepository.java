package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.UserSolution;

import java.util.UUID;

public interface UserSolutionRepository extends JpaRepository<UserSolution, UUID> {
}
