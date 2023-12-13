package umc.study.dto.member;

import lombok.Data;
import umc.study.domain.Member;


@Data
public class CreateMemberRequest {
    // 회원 등록(POST) - 요청
    private String name;
    private String address;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .address(address)
                .build();
    }
}


