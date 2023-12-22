package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MemberPrefer;

public interface MemberPreferRepository extends JpaRepository<MemberPrefer, Long> {
}
