package umc.study.dto.store;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStoreResponse {

    @ApiModelProperty(value = "가게 ID", example = "1")
    private Long storeId;
}
