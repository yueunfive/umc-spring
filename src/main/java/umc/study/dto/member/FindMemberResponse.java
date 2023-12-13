package umc.study.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMemberResponse {
    // 회원 조회(GET) - 응답

    private Long id;
    private String name;
    private String address;
}