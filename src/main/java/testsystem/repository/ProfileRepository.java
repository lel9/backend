package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.Profile;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
