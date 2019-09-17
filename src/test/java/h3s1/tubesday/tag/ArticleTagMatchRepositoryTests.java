package h3s1.tubesday.tag;

import h3s1.tubesday.article.Article;
import h3s1.tubesday.article.ArticleRepository;
import h3s1.tubesday.user.User;
import h3s1.tubesday.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleTagMatchRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTagMatchRepository articleTagMatchRepository;

    private static final String EMAIL = "lee@zerobell.xyz";
    private static final String NICKNAME = "이영종";
    private static final String PASSWORD = "1234";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    private static final String TITLE = "안녕하세요";
    private static final String CONTENT = "언제까지 테스트 짜고 있어야 하냐";

    // [온스테이지] 316. 넬 - 기억을 걷는 시간
    private static final String VIDEO_ID = "83IfZhO4Pd0";

    private User author;
    private Article article;

    @Before
    public void init() {
        author = User.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        article = Article.builder()
                .title(TITLE)
                .author(author)
                .content(CONTENT)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .videoId(VIDEO_ID)
                .tagMatchList(new ArrayList<>())
                .build();

        userRepository.save(author);
        articleRepository.save(article);
    }

    @Test
    @Transactional
    public void save_and_findByArticleNo_single_test() {
        Tag tag = Tag.builder()
                .tagName("온스테이지")
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        tagRepository.save(tag);

        ArticleTagMatch match = ArticleTagMatch.builder()
                .article(article)
                .tag(tag)
                .build();

        articleTagMatchRepository.save(match);

        ArticleTagMatch foundMatch = articleTagMatchRepository.findByTag_TagName(match.getTag().getTagName()).get(0);
        assertEquals(match, foundMatch);
    }

    @Test
    @Transactional
    public void save_and_findByArticleNo_multiple_test() {
        Tag tag1 = Tag.builder()
                .tagName("온스테이지")
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        Tag tag2 = Tag.builder()
                .tagName("넬")
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        Tag tag3 = Tag.builder()
                .tagName("기억을걷는시간")
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);

        ArticleTagMatch match1 = ArticleTagMatch.builder()
                .article(article)
                .tag(tag1)
                .build();

        ArticleTagMatch match2 = ArticleTagMatch.builder()
                .article(article)
                .tag(tag2)
                .build();

        ArticleTagMatch match3 = ArticleTagMatch.builder()
                .article(article)
                .tag(tag3)
                .build();

        articleTagMatchRepository.save(match1);
        articleTagMatchRepository.save(match2);
        articleTagMatchRepository.save(match3);

        ArticleTagMatch foundMatch1 = articleTagMatchRepository.findByTag_TagName(match1.getTag().getTagName()).get(0);
        ArticleTagMatch foundMatch2 = articleTagMatchRepository.findByTag_TagName(match2.getTag().getTagName()).get(0);
        ArticleTagMatch foundMatch3 = articleTagMatchRepository.findByTag_TagName(match3.getTag().getTagName()).get(0);

        assertEquals(match1, foundMatch1);
        assertEquals(match2, foundMatch2);
        assertEquals(match3, foundMatch3);
    }

    @Test
    @Transactional
    public void save_and_findByTagName_multiple_test() {
        Article article1 = article;
        Article article2 = Article.builder()
                .title("이건 두번째 글입니다.")
                .author(author)
                .content("불만있냐")
                .videoId("IDD5_z3kKCU")
                .comments(new ArrayList<>())
                .tagMatchList(new ArrayList<>())
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();
        Article article3 = Article.builder()
                .title("이건 세번째 글입니다.")
                .author(author)
                .content("불만있냐")
                .videoId("W6AZscGtRqI")
                .comments(new ArrayList<>())
                .tagMatchList(new ArrayList<>())
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        Tag tag = Tag.builder()
                .tagName("온스테이지")
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        tagRepository.save(tag);

        ArticleTagMatch match1 = ArticleTagMatch.builder()
                .article(article1)
                .tag(tag)
                .build();

        ArticleTagMatch match2 = ArticleTagMatch.builder()
                .article(article2)
                .tag(tag)
                .build();

        ArticleTagMatch match3 = ArticleTagMatch.builder()
                .article(article3)
                .tag(tag)
                .build();

        article1.getTagMatchList().add(match1);
        article2.getTagMatchList().add(match2);
        article3.getTagMatchList().add(match3);

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        List<ArticleTagMatch> foundMatches = articleTagMatchRepository.findByTag_TagName(tag.getTagName());

        assertNotNull(foundMatches);
        assertEquals(3L, foundMatches.size());

        assertEquals(article1, foundMatches.get(0).getArticle());
        assertEquals(article2, foundMatches.get(1).getArticle());
        assertEquals(article3, foundMatches.get(2).getArticle());
    }
}
