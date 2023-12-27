package umc.study.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class StoreReviewPage {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        List<ReviewPreView> reviewList;
        Integer listSize;
        Long totalElements;
        Integer totalPage;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreView {
        String username;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
