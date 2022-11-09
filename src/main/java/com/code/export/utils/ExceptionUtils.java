package com.code.export.utils;

import com.code.export.dto.APIResponse;
import com.code.export.dto.ErrorDTO;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
public class ExceptionUtils {

    public static APIResponse<?> mapToAPIResponse(ObjectError error) {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        ErrorDTO errorDTO = ErrorDTO.builder()
                .fieldName(fieldName)
                .errorCode(errorMessage)
                .build();
        return APIResponse.builder()
                .response(errorDTO)
                .build();
    }
}
