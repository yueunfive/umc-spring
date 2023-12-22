package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.SocialType;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberPrefer;

import java.util.List;


@Data
public class CreateMemberRequest {
    // 회원 등록(POST) - 요청

    @ApiModelProperty(value = "회원 이름", example = "오유은")
    private String name;

    @ApiModelProperty(value = "지역", example = "서울")
    private String address;

    @ApiModelProperty(value = "상세 주소", example = "동작구 상도동")
    private String specAddress;

    // ENUM과는 상이한 값 입력시 예외 터트리기
    @ApiModelProperty(value = "성별 (가능한 값: MALE, FEMALE, NONE)", example = "MALE", allowableValues = "MALE, FEMALE, NONE")
    private Gender gender;

    @ApiModelProperty(value = "이메일", example = "example1234@naver.com")
    private String email;

    // TermsList랑 FoodCategroyList 스웨거 value, example 안 됨
    @ApiModelProperty(value = "동의한 선택 약관 id (1~2)\n" +
            "1 : 위치정보 제공\n" +
            "2 : 마케팅 수신 동의", example = "[1, 2]")
    private List<Long> TermsList;

    @ApiModelProperty(value = "선호하는 음식 id (1~12)\n" +
            "1: 한식\n" +
            "2: 일식\n" +
            "3: 중식\n" +
            "4: 양식\n" +
            "5: 치킨\n" +
            "6: 분식\n" +
            "7: 고기/구이\n" +
            "8: 도시락\n" +
            "9: 야식(족발, 보쌈)\n" +
            "10: 패스트푸드\n" +
            "11: 디저트\n" +
            "12: 아시안 푸드", example = "[1, 5, 8]")
    private List<Long> FoodCategoryList;


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .address(address)
                .specAddress(specAddress)
                .gender(gender)
                .email(email)
                .build();
    }
}


