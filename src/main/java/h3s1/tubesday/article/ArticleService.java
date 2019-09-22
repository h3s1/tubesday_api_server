package h3s1.tubesday.article;

import h3s1.tubesday.tag.ArticleTagMatch;
import h3s1.tubesday.tag.ArticleTagMatchRepository;
import h3s1.tubesday.tag.Tag;
import h3s1.tubesday.tag.TagRepository;
import h3s1.tubesday.user.User;
import h3s1.tubesday.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private TagRepository tagRepository;
    private ArticleTagMatchRepository articleTagMatchRepository;
    private UserRepository userRepository;

    @Autowired
    ArticleService(ArticleRepository articleRepository, TagRepository tagRepository, ArticleTagMatchRepository articleTagMatchRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.articleTagMatchRepository = articleTagMatchRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle(long userNo, String title, String content, String videoId, List<String> tags) {
        final Optional<User> optionalAuthor = userRepository.findById(userNo);

        if (!optionalAuthor.isPresent()) {
            throw new RuntimeException("User not found!!");
        }

        final User author = optionalAuthor.get();

        final List<ArticleTagMatch> articleTagMatches = new ArrayList<>();
        final Article article = Article.builder()
                .author(author)
                .title(title)
                .content(content)
                .videoId(videoId)
                .build();

        for (String tagName : tags) {
            Tag tag = tagRepository.findByTagName(tagName);

            if (tag == null) {
                tag = new Tag();
                tag.setTagName(tagName);
                tagRepository.save(tag);
            }
            ArticleTagMatch match = ArticleTagMatch.builder()
                    .tag(tag)
                    .article(article)
                    .build();

            articleTagMatches.add(match);
        }
        article.setTagMatchList(articleTagMatches);

        return articleRepository.save(article);
    }

}
