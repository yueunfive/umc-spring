package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Owner;
import umc.study.domain.Region;
import umc.study.repository.OwnerRepostiory;
import umc.study.repository.RegionRepository;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public Region findById(long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
