package umc.study.dto.review;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.MemberService;
import umc.study.service.StoreService;

@Data
@RequiredArgsConstructor
public class CreateReviewRequest {
    // 리뷰 등록(POST) - 요청
    private String body;
    private Float score;
    private Long storeId;
    private Long memberId;
}
