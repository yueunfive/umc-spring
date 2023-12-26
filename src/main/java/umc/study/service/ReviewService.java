package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.exception.CustomErrorCode;
import umc.study.exception.CustomException;
import umc.study.repository.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreService storeService;
    private final MemberService memberService;

    //등록
    @Transactional
    public Long save(CreateReviewRequest request) {
        Store store = storeService.findById(request.getStoreId());
        Member member = memberService.findById(request.getMemberId());

        Float score = request.getScore();

        if (score == null || score < 1 || score > 5  || !isInteger(score)) {
            throw new CustomException(CustomErrorCode.SCORE_NUM_ERROR);
        }

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();

        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }

    // score가 정수인지 체크(save 메서드에서 사용)
    private boolean isInteger(float score) {
        return (score == Math.floor(score)) && !Float.isInfinite(score);
    }
}

