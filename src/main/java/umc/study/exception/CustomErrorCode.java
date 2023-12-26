package umc.study.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {

    // 리뷰 점수
    SCORE_NUM_ERROR(HttpStatus.BAD_REQUEST, "1에서 5 사이의 정수를 입력해주세요.");

    private final HttpStatus httpStatus;
    private final String statusMessage;
}
