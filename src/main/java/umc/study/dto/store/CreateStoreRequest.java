package umc.study.dto.store;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStoreRequest {

    @ApiModelProperty(value = "가게 이름", example = "붕어빵가게")
    private String storeName;

    @ApiModelProperty(value = "가게 주소", example = "동작구 상도동")
    private String storeAddress;

    @ApiModelProperty(value = "지역 ID", example = "1")
    private Long regionId;

    @ApiModelProperty(value = "업주 ID", example = "1")
    private Long ownerId;
}
