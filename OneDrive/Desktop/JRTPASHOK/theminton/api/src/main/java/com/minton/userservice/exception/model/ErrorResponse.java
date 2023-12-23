package com.minton.userservice.exception.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonNaming( PropertyNamingStrategies.UpperCamelCaseStrategy.class )
public class ErrorResponse
{
    private String code;
    private String id;
    private String message;
    private List<ErrorData> errors = new ArrayList<>();
}
