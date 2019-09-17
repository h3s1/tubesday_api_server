package h3s1.tubesday.tag;

import h3s1.tubesday.article.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article_tag_match")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagMatch {

    @Id
    @GeneratedValue
    @Column(name = "match_no")
    private Long matchNo;

    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "article_no")
    private Article article;
}
