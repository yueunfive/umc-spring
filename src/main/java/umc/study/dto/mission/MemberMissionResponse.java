package umc.study.dto.mission;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import umc.study.domain.enums.MissionStatus;

@Data
@Builder
@AllArgsConstructor
public class MemberMissionResponse {
    @ApiModelProperty(value = "사용자에게 할당된 미션 ID", example = "5")
    private Long memberMissionId;

    @ApiModelProperty(value = "미션 성공 유무", example = "COMPLETE", allowableValues = "CHALLENGING, COMPLETE")
    private MissionStatus status;

    @ApiModelProperty(value = "가게 이름", example = "붕어빵가게")
    private String storeName;

    @ApiModelProperty(value = "미션 상세정보", example = "10000원 이상 구매하기")
    private String missionSpec;

    @ApiModelProperty(value = "미션 보상", example = "500")
    private Integer reward;

    @ApiModelProperty(value = "미션 마감기한", example = "2023-12-14")
    private String deadline;
}
