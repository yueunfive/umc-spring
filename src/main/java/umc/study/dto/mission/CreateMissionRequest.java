package umc.study.dto.mission;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CreateMissionRequest {

    @NotNull
    @ApiModelProperty(value = "가게 ID", example = "1")
    private Long storeId;

    @NotNull
    @Min(value = 100)
    @Max(value = 99999)
    @ApiModelProperty(value = "미션 보상", example = "500")
    private Integer reward;

    @NotNull
    @ApiModelProperty(value = "미션 마감기한", example = "2023-12-25")
    @FutureOrPresent
    private LocalDate deadline;

    @NotBlank
    @ApiModelProperty(value = "미션 상세정보", example = "10000원 이상 구매하기")
    private String missionSpec;
}
