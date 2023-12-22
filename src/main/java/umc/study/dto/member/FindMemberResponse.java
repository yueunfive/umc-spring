package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMemberResponse {
    // 회원 조회(GET) - 응답

    @ApiModelProperty(value = "회원 ID", example = "5")
    private Long id;

    @ApiModelProperty(value = "회원 이름", example = "오유은")
    private String name;

    @ApiModelProperty(value = "회고 주소", example = "서울")
    private String address;
}