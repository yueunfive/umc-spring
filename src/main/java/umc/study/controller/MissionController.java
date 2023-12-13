package umc.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.mission.CreateMissionResponse;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.dto.review.CreateReviewResponse;
import umc.study.service.MissionService;
import umc.study.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/api/store/mission")
    public ResponseEntity<CreateMissionResponse> saveMission(@RequestBody @Valid CreateMissionRequest request) {
        Long id = missionService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateMissionResponse(id));
    }
}
