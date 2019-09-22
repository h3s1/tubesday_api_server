package h3s1.tubesday.article;

import h3s1.tubesday.user.User;
import h3s1.tubesday.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTests {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    private static final String USER_EMAIL = "lee@zerobell.xyz";
    private static final String USER_NICKNAME = "이영종";
    private static final String USER_PASSWORD = "1q2w3e4r!";
    private static final String USER_AVATAR = "test.png";

    private static final String TITLE = "본인 방금 튭스데이로 뗴돈 버는 상상함 ㅋ";
    private static final String CONTENT = "하지만 어림도 없지";
    private static final String VIDEO_ID = "24iwWFzaSWU";

    private User author;

    @Before
    public void init() {
        author = userService.createUser(USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_AVATAR);
    }

    @Test
    public void createArticle_test() {
        final List<String> tags = List.of("ASMR", "공부");
        Article article = articleService.createArticle(author.getUserNo(), TITLE, CONTENT, VIDEO_ID, tags);
        assertNotNull(article.getAritcleNo());
        assertEquals(author, article.getAuthor());
        assertEquals(TITLE, article.getTitle());
        assertEquals(CONTENT, article.getContent());
        assertEquals(VIDEO_ID, article.getVideoId());

        assertNotNull(article.getTagMatchList());

        final List<String> savedTags = article.getTagMatchList().stream().map(e -> e.getTag().getTagName()).collect(Collectors.toList());

        assertTrue(savedTags.containsAll(tags));
        assertTrue(tags.containsAll(savedTags));
    }
}
