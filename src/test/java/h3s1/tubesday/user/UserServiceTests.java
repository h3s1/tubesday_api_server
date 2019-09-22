package h3s1.tubesday.user;

import h3s1.tubesday.api.ServiceErrorMessage;
import h3s1.tubesday.api.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void createUser_test() {
        User user = userService.createUser("lee@zerobell.xyz", "12341234", "이영종이담마", "test.png");
        assertNotNull(user.getUserNo());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createUser_withDuplicatedEmailTest() {
        User user = userService.createUser("lee@zerobell.xyz", "12341234", "이영종이담마", "test.png");
        assertNotNull(user.getUserNo());

        userService.createUser("lee@zerobell.xyz", "998877", "이영종이라곰마", "test.png");
    }

    @Test
    public void createUser_withInvalidEmailTest() {
        try {
            userService.createUser("아힝홍항", "112233", "이영종", "test.png");
            fail();
        } catch (ServiceException ex) {
            assertEquals(ServiceErrorMessage.EMAIL_TYPE_INVALID, ex.getCode());
        }
    }

    @Test
    public void createUser_withInvalidPasswordTest() {
        try {
            userService.createUser("lee@zerobell.xyz", "", "이영종", "test.png");
            fail();
        } catch (ServiceException ex) {
            assertEquals(ServiceErrorMessage.PASSWORD_TYPE_INVALID, ex.getCode());
        }
    }
}
