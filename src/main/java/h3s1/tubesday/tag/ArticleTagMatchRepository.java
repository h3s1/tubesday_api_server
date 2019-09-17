package h3s1.tubesday.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleTagMatchRepository extends JpaRepository<ArticleTagMatch, Long> {
    List<ArticleTagMatch> findByArticle_AritcleNo(Long articleNo);
    List<ArticleTagMatch> findByTag_TagName(String tagName);
}
