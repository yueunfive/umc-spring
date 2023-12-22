package umc.study.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.review.CreateReviewResponse;
import umc.study.service.ReviewService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Api(tags = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/review")
    @ApiOperation(value = "리뷰 등록", notes = "새로운 리뷰 정보를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value="등록할 리뷰 정보", required = true, paramType = "body", dataTypeClass = CreateReviewRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "리뷰 등록 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<CreateReviewResponse> saveReview(@RequestBody @Valid CreateReviewRequest request) {
        Long id = reviewService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateReviewResponse(id));
    }
}
