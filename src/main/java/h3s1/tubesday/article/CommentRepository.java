package h3s1.tubesday.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle_AritcleNo(Long articleNo);
}
