package testsystem.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    USER, ADMIN;

    private String role;

    static {
        USER.role = "ROLE_USER";
        ADMIN.role = "ROLE_ADMIN";
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

}