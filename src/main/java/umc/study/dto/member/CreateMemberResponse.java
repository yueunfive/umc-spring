package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMemberResponse {
    // 회원 등록(POST) - 응답

    @ApiModelProperty(value = "회원 ID", example = "1")
    private Long memberId;
}