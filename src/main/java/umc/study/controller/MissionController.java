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
import umc.study.dto.member.*;
import umc.study.dto.mission.*;
import umc.study.service.MemberMissionService;
import umc.study.service.MissionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;
    private final MemberMissionService memberMissionService;

    @PostMapping("/mission")
    @ApiOperation(value = "회원 미션 등록", notes = "회원 정보에 미션을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "등록할 회원-미션 ID", required = true, paramType = "body", dataTypeClass = CreateMemberMissionRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "이미 해당 미션을 수행 중 입니다."),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<MessageResponse> saveMemberMission(@RequestBody @Valid CreateMemberMissionRequest request) {
        missionService.addMission(request);
        MessageResponse messageResponse = new MessageResponse("미션이 추가되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }

    @PostMapping("/store/mission")
    @ApiOperation(value = "가게 미션 등록", notes = "가게 정보에 미션을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "가게에 등록할 미션 정보", required = true, paramType = "body", dataTypeClass = CreateMissionRequest.class)
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

    @GetMapping(value = "/missions/region", params = "region")
    @ApiOperation(value = "지역별 미션 조회", notes = "지역별 미션 목록을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "region", value = "지역 이름", example = "서울", required = true, paramType = "query", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "지역별 미션 조회 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<Result<RegionMissionResponse>> getMissionsByRegion(@RequestParam("region") String region) {
        List<RegionMissionResponse> missions = missionService.getMissionsByRegion(region);

        return ResponseEntity.ok()
                .body(new Result<>(missions.size(), missions));
    }

    @GetMapping(value = "/{memberId}/missions")
    @ApiOperation(value = "사용자 미션 조회", notes = "사용자의 미션 목록을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "조회할 페이지 (1부터 시작)", example = "1", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "한 페이지에 조회할 개수", example = "10", paramType = "query", dataTypeClass = Integer.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용자 미션 조회 성공"),
            @ApiResponse(code = 400, message = "사용자 정보를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<Page<MemberMissionPage.MemberMissionListDTO>> getMissionList(@RequestParam(defaultValue = "1") int pageNumber,
                                                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                                                 @PathVariable Long memberId) {

        Pageable pageable = getPageRequestByCreateDate(pageNumber - 1, pageSize);
        Page<MemberMissionPage.MemberMissionListDTO> response = memberMissionService.getPage(memberId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PatchMapping("/mission/complete/{memberMissionId}")
    @ApiOperation(value = "미션 성공 인증", notes = "미션 성공 인증 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberMissionId", value = "사용자에게 할당된 미션 ID", example = "1", required = true, paramType = "path", dataTypeClass = Long.class)
    })
    @ApiResponses({
            @ApiResponse(code = 202, message = "미션 성공 요청이 승인되었습니다."),
            @ApiResponse(code = 400, message = "해당 ID는 유효하지 않습니다."),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<MessageResponse> patchCompletion(@PathVariable Long memberMissionId) {
        memberMissionService.updateStatus(memberMissionId);
        MessageResponse messageResponse = new MessageResponse("미션 성공 요청이 승인되었습니다.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(messageResponse);
    }


    private Pageable getPageRequestByCreateDate(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber, pageSize, Sort.by(
                Sort.Order.desc("createdAt")
        ));
    }
}
