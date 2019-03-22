package testsystem.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import testsystem.domain.User;
import testsystem.dto.UserDTO;
import testsystem.dto.View;
import testsystem.security.JWTFactory;
import testsystem.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static testsystem.security.SecurityConstants.SIGN_UP_URL;

@RestController
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(SIGN_UP_URL)
    @JsonView(View.UI_REG.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody @Valid UserDTO user, HttpServletRequest request, HttpServletResponse response) {
        User registeredUser = userService.registerUser(User.fromUserDTO(user));
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
        }

        String token = JWTFactory.create(registeredUser.getUsername());
        return new UserDTO(
                registeredUser.getUsername(),
                registeredUser.getEmail(),
                registeredUser.getRole().getAuthority(),
                token);
    }

}
