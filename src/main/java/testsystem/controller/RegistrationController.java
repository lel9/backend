package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import testsystem.domain.User;
import testsystem.dto.UserDTO;
import testsystem.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody @Valid UserDTO user) {
        User registeredUser = userService.registerUser(User.fromUserDTO(user));
        return new UserDTO(registeredUser.getUsername(), registeredUser.getEmail(), registeredUser.getRole().getAuthority());
    }

}
