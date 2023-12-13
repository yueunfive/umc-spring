package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.dto.review.CreateReviewRequest;
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

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();

        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }
}
