package umc.study.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.dto.mission.MemberMissionResponse;

import java.util.List;

public class MemberMissionPage {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDTO {
        List<MemberMissionResponse> memberMissionList;
        Integer listSize;
        Long totalElements;
        Integer totalPage;
        Boolean isFirst;
        Boolean isLast;
    }
}
