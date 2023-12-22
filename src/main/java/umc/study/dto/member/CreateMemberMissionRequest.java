package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberMission;

@Data
@AllArgsConstructor
public class CreateMemberMissionRequest {
    // 회원 미션 등록(POST) - 요청

    @ApiModelProperty(value = "회원 id", example = "1")
    private Long memberId;

    @ApiModelProperty(value = "미션 id", example = "1")
    private Long missionId;
}
