package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import testsystem.domain.EmailToken;
import testsystem.domain.Profile;
import testsystem.domain.User;
import testsystem.exception.EmailAlreadyExistsException;
import testsystem.exception.UserAlreadyExistsException;
import testsystem.repository.EmailTokenRepository;
import testsystem.repository.ProfileRepository;
import testsystem.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EmailTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        String username = user.getUsername();
        String email = user.getEmail();

        if (userRepository.findByUsername(username) != null)
            throw new UserAlreadyExistsException(username);
        if (userRepository.findByEmail(email) != null)
            throw new EmailAlreadyExistsException(email);

        user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));

        Profile profile = new Profile();
        Profile saved = profileRepository.save(profile);
        user.setProfile(saved);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public User findUserByVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    public EmailToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void createEmailToken(User user, String token) {
        EmailToken emailToken = new EmailToken(token, user);
        tokenRepository.save(emailToken);
    }

    @Override
    public void activateUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
