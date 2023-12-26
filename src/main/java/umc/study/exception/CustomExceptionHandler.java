package umc.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleException(CustomException e, HttpServletRequest request) {

        log.error("errorCode: {}, url: {}, message: {}",
                e.getCustomErrorCode(), request.getRequestURI(), e.getDetailMessage());

        CustomErrorResponse errorResponse = new CustomErrorResponse(
                e.getCustomErrorCode(),
                e.getCustomErrorCode().getStatusMessage()
        );

        return ResponseEntity
                .status(e.getCustomErrorCode().getHttpStatus())
                .body(errorResponse);
    }
}
