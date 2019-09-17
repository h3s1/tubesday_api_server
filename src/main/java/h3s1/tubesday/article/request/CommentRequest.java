package h3s1.tubesday.article.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long articleNo;
    private Long userNo;
    private String content;
}
