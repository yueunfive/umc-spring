package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Mission;
import umc.study.domain.Owner;
import umc.study.repository.OwnerRepostiory;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepostiory ownerRepostiory;

    public Owner findById(long id) {
        return ownerRepostiory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
