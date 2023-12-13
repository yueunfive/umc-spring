package umc.study.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMemberResponse {
    // 회원 등록(POST) - 응답
    private Long memberId;
}