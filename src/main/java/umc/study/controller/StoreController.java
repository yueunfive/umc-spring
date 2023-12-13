package umc.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.mission.CreateMissionResponse;
import umc.study.dto.store.CreateStoreRequest;
import umc.study.dto.store.CreateStoreResponse;
import umc.study.service.ReviewService;
import umc.study.service.StoreService;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/api/store")
    public ResponseEntity<CreateStoreResponse> saveMission(@RequestBody @Valid CreateStoreRequest request) {
        Long id = storeService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateStoreResponse(id));
    }
}
