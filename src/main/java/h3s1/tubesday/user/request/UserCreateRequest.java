package h3s1.tubesday.user.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String nickname;
    private String password;
    private String avatarUrl;
}
