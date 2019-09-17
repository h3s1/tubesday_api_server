package h3s1.tubesday.user.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long userNo; // this is nullable. Only for update
    private String email;
    private String nickname;
    private String password;
    private String avatarUrl;
}
