package umc.study.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.review.CreateReviewResponse;
import umc.study.dto.store.StoreReviewPage;
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
            @ApiImplicitParam(name = "request", value = "등록할 리뷰 정보", required = true, paramType = "body", dataTypeClass = CreateReviewRequest.class)
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

    @GetMapping("/api/{storeId}/reviews")
    @ApiOperation(value = "리뷰 목록 조회", notes = "해당 가게에 등록된 리뷰 목록을 최신순으로 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "조회할 페이지 (1부터 시작)", example = "1", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "한 페이지에 조회할 개수", example = "10", paramType = "query", dataTypeClass = Integer.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "리뷰 목록 조회 성공"),
            @ApiResponse(code = 400, message = "가게 정보를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<Page<StoreReviewPage.ReviewListDTO>> getReviewList(@RequestParam(defaultValue = "1") int pageNumber,
                                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                                             @PathVariable Long storeId) {
        Pageable pageable = getPageRequestByCreateDate(pageNumber - 1, pageSize);
        Page<StoreReviewPage.ReviewListDTO> response = reviewService.getPage(storeId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Pageable getPageRequestByCreateDate(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber, pageSize, Sort.by(
                Sort.Order.desc("createdAt")
        ));
    }
}
