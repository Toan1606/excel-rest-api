package com.code.export.exception;

import com.code.export.dto.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * this method handle exception class
     * @return response entity of APIResponse from exception raise
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handler() {
        APIResponse<Object> apiResponse = APIResponse.builder()
                .response("Exception Handler")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
