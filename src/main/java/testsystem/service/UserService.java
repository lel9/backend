package testsystem.service;

import testsystem.domain.User;

public interface UserService {
    public User registerUser(User user);
    public User findByUsername(String username);
}
