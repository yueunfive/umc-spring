package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Owner;
import umc.study.domain.Region;
import umc.study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByNameAndAddressAndRegionAndOwner(String name, String address, Region region, Owner owner);
}


