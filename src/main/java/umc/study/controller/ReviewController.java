package umc.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.review.CreateReviewResponse;
import umc.study.service.ReviewService;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/review")
    public ResponseEntity<CreateReviewResponse> saveReview(@RequestBody @Valid CreateReviewRequest request) {
        Long id = reviewService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateReviewResponse(id));
    }
}
