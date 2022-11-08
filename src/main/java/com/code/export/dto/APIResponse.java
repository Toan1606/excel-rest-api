package com.code.export.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class APIResponse<T> {

    private String status;

    private List<ErrorDTO> errors;

    private T response;
}
