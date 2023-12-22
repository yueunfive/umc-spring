package umc.study.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.member.CreateMemberMissionRequest;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.mission.CreateMissionResponse;
import umc.study.service.MissionService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/api/store/mission")
    @ApiOperation(value = "가게 미션 등록", notes = "가게 정보에 미션을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value="가게에 등록할 미션 정보", required = true, paramType = "body", dataTypeClass = CreateMissionRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "가게 미션 등록 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<CreateMissionResponse> saveMission(@RequestBody @Valid CreateMissionRequest request) {
        Long id = missionService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateMissionResponse(id));
    }
}
