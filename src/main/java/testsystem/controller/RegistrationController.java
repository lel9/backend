package testsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import testsystem.domain.User;
import testsystem.dto.UserDTO;
import testsystem.security.JWTFactory;
import testsystem.service.UserServiceImpl;
import testsystem.util.GenericResponse;

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
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse signUp(@RequestBody @Valid UserDTO user, HttpServletRequest request, HttpServletResponse response) {
        User registeredUser = userService.registerUser(User.fromUserDTO(user));
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
        }

        String token = JWTFactory.create(registeredUser.getUsername());
        return new GenericResponse(new UserDTO(
                registeredUser.getUsername(),
                registeredUser.getEmail(),
                registeredUser.getRole().getAuthority(),
                token));
    }

}
