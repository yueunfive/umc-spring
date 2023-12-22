package umc.study.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.mission.CreateMissionResponse;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.store.CreateStoreRequest;
import umc.study.dto.store.CreateStoreResponse;
import umc.study.service.ReviewService;
import umc.study.service.StoreService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Store", description = "가게 관련 API")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/api/store")
    @ApiOperation(value = "가게 등록", notes = "새로운 가게 정보를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value="등록할 가게 정보", required = true, paramType = "body", dataTypeClass = CreateStoreRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "가게 등록 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<CreateStoreResponse> saveMission(@RequestBody @Valid CreateStoreRequest request) {
        Long id = storeService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateStoreResponse(id));
    }
}
