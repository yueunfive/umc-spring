package umc.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.study.dto.member.MessageResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<MessageResponse> handleIllegalStateException(IllegalStateException ex) {
        MessageResponse messageResponse = new MessageResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        MessageResponse messageResponse = new MessageResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponse);
    }
}