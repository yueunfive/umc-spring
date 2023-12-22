package umc.study.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.domain.Member;
import umc.study.dto.member.*;
import umc.study.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = "Member", description = "사용자 관련 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/mission")
    @ApiOperation(value = "회원 미션 등록", notes = "회원 정보에 미션을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value="등록할 회원-미션 ID", required = true, paramType = "body", dataTypeClass = CreateMemberMissionRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 미션 등록 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<MessageResponse> saveMemberMission(@RequestBody @Valid CreateMemberMissionRequest request) {
        String message = memberService.addMission(request);
        MessageResponse messageResponse = new MessageResponse(message);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageResponse);
    }

    @GetMapping("/api/members")
    @ApiOperation(value = "회원 전체 조회", notes = "전체 회원 수와 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 전체 조회 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<Result> members() {
        List<Member> findMembers = memberService.findMembers();
        List<FindMemberResponse> collect = findMembers.stream()
                .map(m -> new FindMemberResponse(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(new Result(collect.size(), collect));
    }

    @PostMapping("/api/signup")
    @ApiOperation(value = "회원 가입", notes = "새로운 회원 정보를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value="등록할 회원 정보", required = true, paramType = "body", dataTypeClass = CreateMemberRequest.class)
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 등록 성공"),
            @ApiResponse(code = 500, message = "서버 내 오류")
    })
    public ResponseEntity<CreateMemberResponse> saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Long id = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateMemberResponse(id));
    }

}
