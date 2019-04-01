package testsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import testsystem.util.GenericResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException {

        GenericResponse responseBody = new GenericResponse("Неверный логин или пароль", "AuthorizationFailure");

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(responseBody);

        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.getWriter().write(body);

    }
}
