package testsystem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "email_tokens")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EmailToken {
    private static final long EXPIRATION = 60 * 60 * 24; // 24 часа

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Long creation_date;

    private Long calculateExpiryDate() {
        return creation_date + EXPIRATION;
    }

    public EmailToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.creation_date = System.currentTimeMillis() / 1000;
    }

    public boolean isTokenExpired() {
        return System.currentTimeMillis() / 1000 > calculateExpiryDate();
    }

}