package umc.study.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberMission;

@Data
@AllArgsConstructor
public class CreateMemberMissionRequest {
    // 회원 미션 등록(POST) - 요청
    private Long memberId;
    private Long missionId;
}
