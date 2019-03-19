package testsystem.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    user, admin;

    private String role;

    static {
        user.role = "ROLE_USER";
        admin.role = "ROLE_ADMIN";
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

}