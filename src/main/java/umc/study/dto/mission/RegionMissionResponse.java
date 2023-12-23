package umc.study.dto.mission;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegionMissionResponse {

    @ApiModelProperty(value = "미션 ID", example = "5")
    private Long missionId;

    @ApiModelProperty(value = "가게 이름", example = "붕어빵가게")
    private String storeName;

    @ApiModelProperty(value = "미션 상세정보", example = "10000원 이상 구매하기")
    private String missionSpec;

    @ApiModelProperty(value = "미션 보상", example = "500")
    private Integer reward;

    @ApiModelProperty(value = "미션 마감기한", example = "2023-12-14 16:33:51.249445")
    private String deadline;
}
