package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import testsystem.domain.User;
import testsystem.exception.EmailAlreadyExistsException;
import testsystem.exception.UserAlreadyExistsException;
import testsystem.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
