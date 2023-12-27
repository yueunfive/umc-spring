package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.store.StoreReviewPage;
import umc.study.exception.CustomErrorCode;
import umc.study.exception.CustomException;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreService storeService;
    private final MemberService memberService;
    private final StoreRepository storeRepository;

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


    public Page<StoreReviewPage.ReviewListDTO> getPage(Long storeId, Pageable pageable) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 정보를 찾을 수 없음"));

        Page<Review> reviewList = reviewRepository.findByStoreId(storeId, pageable);

        List<StoreReviewPage.ReviewPreView> reviewPreviews = reviewList.getContent().stream()
                .map(review -> StoreReviewPage.ReviewPreView.builder()
                        .username(review.getMember().getName())
                        .score(review.getScore())
                        .body(review.getBody())
                        .createdAt(LocalDate.from(review.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());

        StoreReviewPage.ReviewListDTO listDTO = StoreReviewPage.ReviewListDTO.builder()
                .reviewList(reviewPreviews)
                .listSize(reviewPreviews.size())
                .totalElements(reviewList.getTotalElements())
                .totalPage(reviewList.getTotalPages())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();

        return new PageImpl<>(Collections.singletonList(listDTO), pageable, reviewList.getTotalElements());
    }

}

