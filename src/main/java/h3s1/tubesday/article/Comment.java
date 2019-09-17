package h3s1.tubesday.article;

import h3s1.tubesday.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_no")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "article_no")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User author;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
