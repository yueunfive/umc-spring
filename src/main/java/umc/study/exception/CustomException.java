package umc.study.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException { // runtimeException 싱속

    private final CustomErrorCode customErrorCode;
    private final String detailMessage;
    private final HttpStatus httpStatus;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getStatusMessage()); // runtimeException
        this.customErrorCode = customErrorCode;
        this.httpStatus = customErrorCode.getHttpStatus();
        this.detailMessage = customErrorCode.getStatusMessage();
    }
}
