package umc.study.dto.mission;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMissionRequest {

    private Long storeId;
    private Integer reward;
    private LocalDate deadline;
    private String missionSpec;
}
