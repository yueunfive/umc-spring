package umc.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.domain.Member;
import umc.study.dto.member.*;
import umc.study.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/mission")
    public ResponseEntity<MessageResponse> saveMemberMission(@RequestBody @Valid CreateMemberMissionRequest request) {
        String message = memberService.addMission(request);
        MessageResponse messageResponse = new MessageResponse(message);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageResponse);
    }

    @GetMapping("/api/members")
    public ResponseEntity<Result> members() {
        List<Member> findMembers = memberService.findMembers();
        List<FindMemberResponse> collect = findMembers.stream()
                .map(m -> new FindMemberResponse(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(new Result(collect.size(), collect));
    }

    @PostMapping("/api/member")
    public ResponseEntity<CreateMemberResponse> saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Long id = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateMemberResponse(id));
    }

}
