package umc.study.dto.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReviewResponse {
    // 리뷰 등록(POST) - 응답

    @ApiModelProperty(value = "리뷰 ID", example = "1")
    private Long reviewId;
}
