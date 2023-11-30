package umc.study.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto { // 회원가입
        private String name;
        private String gender;
        private String birthdate;
        private String address;
        private List<String> preferredFoods;
        private Agreements agreements;

        @Getter
        public static class Agreements {
            private boolean optionalAgreement1;
            private boolean optionalAgreement2;
        }
    }

}
