package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.dto.member.CreateMemberRequest;
import umc.study.dto.member.CreateMemberMissionRequest;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionService missionService;
    private final MemberMissionRepository memberMissionRepository;


    //등록
    @Transactional
    public Long join(CreateMemberRequest request) {
        validateDuplicateMember(request); //중복 회원 검증

        Member member = memberRepository.save(request.toEntity());
        return member.getId();
    }

    private void validateDuplicateMember(CreateMemberRequest request) {
        List<Member> findMembers = memberRepository.findByName(request.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단일 조회
    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    //미션 추가
    @Transactional
    public Long addMission(CreateMemberMissionRequest request) {
        Member member = findById(request.getMemberId());
        Mission mission = missionService.findById(request.getMissionId());

        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return savedMemberMission.getId();
    }
}
