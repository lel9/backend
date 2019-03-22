package testsystem.event;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import testsystem.domain.User;
import testsystem.service.UserServiceImpl;

@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private User user;

    @Autowired
    UserServiceImpl service;

    public OnRegistrationCompleteEvent(User user, String appUrl) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
    }

}