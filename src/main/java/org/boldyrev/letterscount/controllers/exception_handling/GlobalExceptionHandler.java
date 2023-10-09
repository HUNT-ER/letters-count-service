package org.boldyrev.letterscount.controllers.exception_handling;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.boldyrev.letterscount.exceptions.ValidationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleException(ValidationException e) {
        log.debug(e.getMessage());
        return ResponseEntity.badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorResponse(e.getMessage(), LocalDateTime.now()));
    }
}
