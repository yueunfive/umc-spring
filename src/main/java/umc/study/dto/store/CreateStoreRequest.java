package umc.study.dto.store;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateStoreRequest {

    @ApiModelProperty(value = "가게 이름", example = "붕어빵가게")
    @NotBlank
    private String storeName;

    @ApiModelProperty(value = "가게 주소", example = "동작구 상도동")
    @NotBlank
    private String storeAddress;

    @ApiModelProperty(value = "지역 ID", example = "1")
    @NotNull
    private Long regionId;

    @ApiModelProperty(value = "업주 ID", example = "1")
    @NotNull
    private Long ownerId;
}
