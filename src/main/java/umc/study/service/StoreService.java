package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.*;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.store.CreateStoreRequest;
import umc.study.repository.StoreRepository;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final RegionService regionService;
    private final OwnerService ownerService;

    //단일 조회
    public Store findById(long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    @Transactional
    public Long save(CreateStoreRequest request) {
        Region region = regionService.findById(request.getRegionId());
        Owner owner = ownerService.findById(request.getOwnerId());

        Store existingStore = storeRepository.findByNameAndAddressAndRegionAndOwner(
                request.getStoreName(),
                request.getStoreAddress(),
                region,
                owner
        );

        if (existingStore != null) {
            throw new IllegalStateException("이미 등록된 가게 정보입니다.");
        }

        Store store = Store.builder()
                .name(request.getStoreName())
                .address(request.getStoreAddress())
                .region(region)
                .owner(owner)
                .build();

        Store savedStore = storeRepository.save(store);

        return savedStore.getId();
    }
}
