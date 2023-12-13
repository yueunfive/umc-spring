package umc.study.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReviewResponse {
    // 리뷰 등록(POST) - 응답
    private Long reviewId;
}
