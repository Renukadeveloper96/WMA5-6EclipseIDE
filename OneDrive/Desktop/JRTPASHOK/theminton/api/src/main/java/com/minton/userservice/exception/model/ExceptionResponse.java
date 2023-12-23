package com.minton.userservice.exception.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExceptionResponse
{

    private String errorCode;
    private String errorDescription;
    private List<String> errors = new ArrayList<>();

}
