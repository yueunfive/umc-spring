package umc.study.dto.store;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStoreRequest {

    private String name;
    private String address;
    private Long regionId;
    private Long ownerId;
}
