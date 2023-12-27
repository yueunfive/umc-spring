package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {

    @ApiModelProperty(value = "응답 메시지", example="success")
    private String message;
}
