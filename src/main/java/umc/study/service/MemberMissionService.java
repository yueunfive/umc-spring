package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.dto.mission.MemberMissionPage;
import umc.study.dto.mission.MemberMissionResponse;
import umc.study.dto.store.StoreReviewPage;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public Page<MemberMissionPage.MemberMissionListDTO> getPage(Long memberId, Pageable pageable) {
        Page<MemberMission> memberMissionList = memberMissionRepository.findByMemberId(memberId, pageable);

        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없음"));

        List<MemberMissionResponse> memberMissionResponses = memberMissionList.stream()
                .map(memberMission -> {
                    Mission mission = memberMission.getMission();
                    return MemberMissionResponse.builder()
                            .memberMissionId(memberMission.getId())
                            .status(memberMission.getStatus())
                            .storeName(mission.getStore().getName())
                            .missionSpec(mission.getMissionSpec())
                            .reward(mission.getReward())
                            .deadline(mission.getDeadline().toString()) // 예시로 변환
                            .build();
                })
                .collect(Collectors.toList());

        MemberMissionPage.MemberMissionListDTO listDTO = MemberMissionPage.MemberMissionListDTO.builder()
                .memberMissionList(memberMissionResponses)
                .listSize(memberMissionResponses.size())
                .totalElements(memberMissionList.getTotalElements())
                .totalPage(memberMissionList.getTotalPages())
                .isFirst(memberMissionList.isFirst())
                .isLast(memberMissionList.isLast())
                .build();

        return new PageImpl<>(Collections.singletonList(listDTO), pageable, memberMissionList.getTotalElements());
    }

    public void updateStatus(long id) {
        MemberMission memberMission = memberMissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id+"는 유효하지 않은 ID입니다."));

        memberMission.updateStatusToCompleted();
        memberMissionRepository.save(memberMission);
    }
}
