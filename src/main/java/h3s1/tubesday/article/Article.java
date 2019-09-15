package h3s1.tubesday.article;

import h3s1.tubesday.tag.ArticleTagMatch;
import h3s1.tubesday.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_no")
    private Long aritcleNo;

    @ManyToOne
    @JoinColumn(name="user_no")
    private User author;

    private String title;
    private String content;

    @Column(name = "video_id")
    private String videoId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", targetEntity = ArticleTagMatch.class)
    private List<ArticleTagMatch> tagMatchList;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "view_cnt")
    private Long viewCnt;

    @Column(name = "comment_cnt")
    private Long commentCnt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
