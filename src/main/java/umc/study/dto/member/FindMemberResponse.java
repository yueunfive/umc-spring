package umc.study.dto.member;

        import io.swagger.annotations.ApiModelProperty;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import umc.study.domain.enums.Gender;

@Data
@Builder
@AllArgsConstructor
public class FindMemberResponse {
    // 회원 조회(GET) - 응답

    @ApiModelProperty(value = "회원 ID", example = "5")
    private Long memberId;

    @ApiModelProperty(value = "회원 이름", example = "오유은")
    private String name;

    @ApiModelProperty(value = "회원 주소", example = "서울")
    private String address;

    @ApiModelProperty(value = "상세 주소", example = "동작구 상도동")
    private String specAddress;

    @ApiModelProperty(value = "성별", example = "MALE")
    private Gender gender;

    @ApiModelProperty(value = "이메일", example = "example1234@naver.com")
    private String email;
}