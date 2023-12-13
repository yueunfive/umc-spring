package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Owner;

public interface OwnerRepostiory extends JpaRepository<Owner, Long> {
}
