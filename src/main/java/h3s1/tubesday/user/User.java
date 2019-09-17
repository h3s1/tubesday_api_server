package h3s1.tubesday.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "email")
    private String email;

    @Column
    private String nickname;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "is_admin")
    private boolean isAdmin;
}
