package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.EmailToken;
import testsystem.domain.User;

import java.util.UUID;

public interface EmailTokenRepository extends JpaRepository<EmailToken, UUID> {

    EmailToken findByToken(String token);

    EmailToken findByUser(User user);

}
