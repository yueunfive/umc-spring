package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByStoreId(Long storeId, Pageable pageable);

}
