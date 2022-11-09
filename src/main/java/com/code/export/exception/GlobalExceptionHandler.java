package com.code.export.exception;

import com.code.export.dto.APIResponse;
import com.code.export.utils.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            APIResponse<?> apiResponse = ExceptionUtils.mapToAPIResponse(objectError);
        }
        return ResponseEntity.ok(APIResponse.builder().build());
    }

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
