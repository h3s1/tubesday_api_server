package h3s1.tubesday.article;

import h3s1.tubesday.user.User;
import h3s1.tubesday.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    private User author;
    private Article article;
    private static final String EMAIL = "lee@zerobell.xyz";
    private static final String NICKNAME = "이영종";
    private static final String PASSWORD = "12341234";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    // [온스테이지2.0]백예린 - 지켜줄게
    private static final String VIDEO_ID = "IDD5_z3kKCU";

    @Before
    public void init() {
        author = User.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();
        userRepository.save(author);

        article = Article.builder()
                .title("안녕하세요")
                .author(author)
                .content("만나서 반갑다")
                .videoId(VIDEO_ID)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .likeCnt(0L)
                .viewCnt(0L)
                .build();
    }

    @Test
    @Transactional
    public void save_and_find_test() {
        articleRepository.save(article);

        Long articleNo = article.getAritcleNo();
        assertNotNull(articleNo);

        try {
            Optional<Article> optionalFoundArticle = articleRepository.findById(articleNo);
            assertTrue(optionalFoundArticle.isPresent());
            Article foundArticle = optionalFoundArticle.get();
            assertEquals(article, foundArticle);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Transactional
    public void save_and_delete_test() {
        articleRepository.save(article);

        Long articleNo = article.getAritcleNo();
        assertNotNull(articleNo);

        articleRepository.delete(article);
        assertFalse(articleRepository.findById(articleNo).isPresent());
    }
}
