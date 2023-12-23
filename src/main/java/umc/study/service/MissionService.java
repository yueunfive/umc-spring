package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.dto.member.CreateMemberMissionRequest;
import umc.study.dto.mission.CreateMissionRequest;
import umc.study.dto.mission.RegionMissionResponse;
import umc.study.dto.review.CreateReviewRequest;
import umc.study.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreService storeService;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberService memberService;

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

    // 사용자 미션 추가
    @Transactional
    public void addMission(CreateMemberMissionRequest request) {
        MemberMission existingMemberMission = memberMissionRepository
                .findByMemberIdAndMissionId(request.getMemberId(), request.getMissionId());

        if (existingMemberMission != null) {
                throw new IllegalStateException("이미 해당 미션을 수행 중 입니다.");
        }

        Member member = memberService.findById(request.getMemberId());
        Mission mission = findById(request.getMissionId());

        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();

        memberMissionRepository.save(memberMission);
    }


    public List<RegionMissionResponse> getMissionsByRegion(String regionName) {

        // 지역명으로 해당 지역 엔티티 검색
        Region region = regionRepository.findByName(regionName);

        // 지역이 없을 경우 예외 처리 혹은 빈 리스트 등을 반환할 수 있음
        if (region == null) {
            throw new IllegalArgumentException("Region not found with name: " + regionName);
        }

        // 지역에 속하는 가게 목록을 가져옴
        List<Store> storesInRegion = region.getStoreList();

        // 해당 지역에 속하는 미션들을 RegionMissionResponse DTO로 매핑하여 반환
        return storesInRegion.stream()
                .flatMap(store -> store.getMissionList().stream()
                        .map(mission -> RegionMissionResponse.builder()
                                .missionId(mission.getId())
                                .storeName(store.getName())
                                .missionSpec(mission.getMissionSpec())
                                .reward(mission.getReward())
                                .deadline(mission.getDeadline().toString()) // 예시로 변환
                                .build()))
                .collect(Collectors.toList());
    }
}
