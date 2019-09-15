package h3s1.tubesday.tag;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_no")
    private Long tagNo;

    @Column(name = "tag_name", unique = true)
    private String tagName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleTagMatch> articleTagMatchList;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
