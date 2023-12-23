package com.minton.userservice.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming( PropertyNamingStrategies.UpperCamelCaseStrategy.class )
public class ErrorData
{

    private String errorCode;
    @JsonInclude( JsonInclude.Include.NON_NULL )
    private String message;
    private String path;
    @JsonInclude( JsonInclude.Include.NON_NULL )
    private Object url;
}
