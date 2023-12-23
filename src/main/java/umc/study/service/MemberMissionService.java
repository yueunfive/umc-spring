package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.dto.mission.MemberMissionResponse;
import umc.study.repository.MemberMissionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    public List<MemberMissionResponse> findByMemberId(Long memberId) {
        List<MemberMission> memberMissions = memberMissionRepository.findByMemberId(memberId);

        List<MemberMissionResponse> missionResponses = memberMissions.stream()
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

        return missionResponses;
    }

    public void updateStatus(long id) {
        MemberMission memberMission = memberMissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID는 유효하지 않습니다."));

        memberMission.updateStatusToCompleted();
        memberMissionRepository.save(memberMission);
    }
}
