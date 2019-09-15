package h3s1.tubesday.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private User user;

    private static final String EMAIL = "bnb3456@gmail.com";
    private static final String NICKNAME = "이영종";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();
    private static final String PASSWORD = "1234";

    @Before
    public void init() {
        user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();
    }

    @Test
    public void save_and_findByEmail_test() {
        userRepository.save(user);
        User foundUser = userRepository.findByEmail(EMAIL);
        assertEquals(user, foundUser);
        assertNotNull(user.getUserNo());
    }

    @Test
    public void delete_test() {
        userRepository.save(user);
        userRepository.delete(user);
        User user2 = userRepository.findByEmail(EMAIL);
        assertNull(user2);
    }
}
