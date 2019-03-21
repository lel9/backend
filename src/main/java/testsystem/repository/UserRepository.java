package testsystem.repository;

import org.springframework.data.repository.CrudRepository;
import testsystem.domain.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsername(String username);
    User findByEmail(String email);
}
