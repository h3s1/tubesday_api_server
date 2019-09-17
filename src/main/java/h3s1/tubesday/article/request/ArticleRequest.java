package h3s1.tubesday.article.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private Long articleNo; // this is nullable. Only for update
    private String title;
    @NotNull private Long userNo;
    private String content;
    private String videoId;
    private List<String> tagList;
}
