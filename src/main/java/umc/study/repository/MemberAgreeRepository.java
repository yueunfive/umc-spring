package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberMission;

public interface MemberAgreeRepository extends JpaRepository<MemberAgree, Long> {
}
