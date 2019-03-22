package testsystem.service;

import testsystem.domain.EmailToken;
import testsystem.domain.User;

public interface UserService {

    User registerUser(User user);

    User findByUsername(String username);

    User findUserByVerificationToken(String verificationToken);

    EmailToken getVerificationToken(String token);

    void createEmailToken(User user, String token);

    void activateUser(User user);
}
