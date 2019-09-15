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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    private User user;
    private Article article;

    private static final String EMAIL = "lee@zerobell.xyz";
    private static final String NICKNAME = "이영종";
    private static final String PASSWORD = "1234";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    private static final String TITLE = "안녕하세요.";
    private static final String CONTENT = "만나서 반갑습니다.";

    // [온스테이지2.0]백예린 - 지켜줄게
    private static final String VIDEO_ID = "IDD5_z3kKCU";

    @Before
    public void init() {
        user = User.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();
        article = Article.builder()
                .title(TITLE)
                .content(CONTENT)
                .videoId(VIDEO_ID)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .comments(new ArrayList<>())
                .build();
        userRepository.save(user);
        articleRepository.save(article);
    }

    @Test
    @Transactional
    public void save_and_findByArticle_ArticleNo_test() {
        final String content = "이건 테스트 댓글입니다.";
        Comment comment = Comment.builder()
                .article(article)
                .author(user)
                .content(content)
                .build();

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByArticle_AritcleNo(article.getAritcleNo());
        assertNotNull(comments);
        assertEquals(comment, comments.get(0));
    }

    @Test
    @Transactional
    public void save_and_findByArticle_ArticleNo_multiple_insert_test() {
        final String content1 = "이건 테스트 댓글입니다.";
        final String content2 = "이것도 테스트 댓글입니다.";
        final String content3 = "저것도 테스트 댓글입니다.";

        Comment comment1 = Comment.builder()
                .article(article)
                .author(user)
                .content(content1)
                .build();

        Comment comment2 = Comment.builder()
                .article(article)
                .author(user)
                .content(content2)
                .build();

        Comment comment3 = Comment.builder()
                .article(article)
                .author(user)
                .content(content3)
                .build();

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        List<Comment> comments = commentRepository.findByArticle_AritcleNo(article.getAritcleNo());
        assertNotNull(comments);
        assertEquals(comment1, comments.get(0));
        assertEquals(comment2, comments.get(1));
        assertEquals(comment3, comments.get(2));
    }

    @Test
    @Transactional
    public void save_and_findByArticle_ArticleNo_and_delete_test() {
        final String content = "이건 테스트 댓글입니다.";
        Comment comment = Comment.builder()
                .article(article)
                .author(user)
                .content(content)
                .build();

        commentRepository.save(comment);
        commentRepository.delete(comment);

        List<Comment> comments = commentRepository.findByArticle_AritcleNo(article.getAritcleNo());
        assertNotNull(comments);
        assertEquals(0L, comments.size());
    }

    @Test
    @Transactional
    public void save_and_findByArticle_ArticleNo_multiple_insert_and_delete_one_test() {
        final String content1 = "이건 테스트 댓글입니다.";
        final String content2 = "이것도 테스트 댓글입니다.";
        final String content3 = "저것도 테스트 댓글입니다.";

        Comment comment1 = Comment.builder()
                .article(article)
                .author(user)
                .content(content1)
                .build();

        Comment comment2 = Comment.builder()
                .article(article)
                .author(user)
                .content(content2)
                .build();

        Comment comment3 = Comment.builder()
                .article(article)
                .author(user)
                .content(content3)
                .build();

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        commentRepository.delete(comment2);

        List<Comment> comments = commentRepository.findByArticle_AritcleNo(article.getAritcleNo());
        assertNotNull(comments);
        assertEquals(comment1, comments.get(0));
        assertEquals(comment3, comments.get(1));
    }
}
