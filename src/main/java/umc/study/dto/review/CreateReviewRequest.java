package umc.study.dto.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.MemberService;
import umc.study.service.StoreService;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
@RequiredArgsConstructor
public class CreateReviewRequest {
    // 리뷰 등록(POST) - 요청

    @ApiModelProperty(value = "리뷰 본문", example = "맛있어요👍")
    private String body;

    @ApiModelProperty(value = "리뷰 점수", example = "5", notes = "리뷰 점수는 1에서 5 사이의 값이어야 합니다.")
    @DecimalMin(value = "1", message = "리뷰 점수는 최소 1 이상이어야 합니다.")
    @DecimalMax(value = "5", message = "리뷰 점수는 최대 5 이하여야 합니다.")
    private Float score;

    @ApiModelProperty(value = "가게 ID", example = "1")
    private Long storeId;

    @ApiModelProperty(value = "회원 ID", example = "1")
    private Long memberId;
}
