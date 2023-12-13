package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.repository.MissionRepository;

@RequiredArgsConstructor
@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreService storeService;

    public Mission findById(long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    @Transactional
    public Long save(CreateMissionRequest request) {
        Store store = storeService.findById(request.getStoreId());

        Mission mission = Mission.builder()
                .store(store)
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();

        Mission savedMission = missionRepository.save(mission);

        return savedMission.getId();
    }
}
