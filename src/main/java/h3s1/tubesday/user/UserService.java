package h3s1.tubesday.user;

import h3s1.tubesday.api.ServiceErrorMessage;
import h3s1.tubesday.api.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password, String nickname, String avatarUrl) {
        validateEmail(email);
        validatePassword(password);
        LocalDateTime now = LocalDateTime.now();
        final User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .createdAt(now)
                .updatedAt(now)
                .build();

        userRepository.save(user);
        return user;
    }

    public User getUserByUserNo(long userNo) {
        final Optional<User> user = userRepository.findById(userNo);

        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("That user doesn't exist.");
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUserByUserNo(long userNo, String email, String password, String nickname, String avatarUrl) {
        final Optional<User> optionalUser = userRepository.findById(userNo);
        final User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            updateUser(user, email, password, nickname, avatarUrl);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not exist");
    }

    public User updateUserByEmail(String email, String password, String nickname, String avatarUrl) {
        final User user = userRepository.findByEmail(email);

        updateUser(user, email, password, nickname, avatarUrl);
        return userRepository.save(user);
    }


    public void deleteUser(long userNo) {
        userRepository.deleteById(userNo);
    }

    private void validateEmail(String email) {
        if (email == null) throw new ServiceException(ServiceErrorMessage.EMAIL_TYPE_INVALID, "email is null");
        if (!email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"))
            throw new ServiceException(ServiceErrorMessage.EMAIL_TYPE_INVALID, "Email type doesn't invalid.");
    }

    private void validatePassword(String password) {
        if (password == null || "".equals(password))
            throw new ServiceException(ServiceErrorMessage.PASSWORD_TYPE_INVALID, "password is null");
        if (password.length() < 2 || password.length() > 45)
            throw new ServiceException(ServiceErrorMessage.PASSWORD_TYPE_INVALID, "password length is invalid.");
    }

    private void updateUser(User user, String email, String password, String nickname, String avatarUrl) {
        user.setEmail("".equals(email) ? user.getEmail() : email);
        user.setPassword("".equals(password) ? user.getPassword() : password);
        user.setNickname("".equals(nickname) ? user.getNickname() : nickname);
        user.setPassword("".equals(avatarUrl) ? user.getAvatarUrl() : avatarUrl);
        user.setUpdatedAt(LocalDateTime.now());
    }
}
