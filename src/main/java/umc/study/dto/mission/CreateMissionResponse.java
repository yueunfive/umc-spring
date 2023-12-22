package umc.study.dto.mission;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMissionResponse {

    @ApiModelProperty(value = "미션 ID", example = "1")
    private Long missionId;
}
