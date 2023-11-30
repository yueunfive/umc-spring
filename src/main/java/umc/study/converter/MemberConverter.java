package umc.study.converter;

import umc.study.domain.Member;
import umc.study.dto.MemberResponseDTO;


public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .message("회원가입이 성공적으로 완료되었습니다.")
                .build();
    }
}
