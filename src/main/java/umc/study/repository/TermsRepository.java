package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long> {
}
