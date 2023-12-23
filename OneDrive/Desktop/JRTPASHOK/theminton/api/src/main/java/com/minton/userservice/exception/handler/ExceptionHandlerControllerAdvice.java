package com.minton.userservice.exception.handler;


import com.minton.userservice.constants.ApplicationConstant;
import com.minton.userservice.exception.BadRequestException;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.exception.model.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerControllerAdvice
{

    private final MessageSource messageSource;

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    public @ResponseBody ExceptionResponse handleEnumValidation( final MethodArgumentNotValidException exception,
                                                                 final HttpServletRequest request )
    {

        String message = exception.getBindingResult().getAllErrors().get( 0 ).getDefaultMessage();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorCode( messageSource.getMessage( "InvalidInput_code", null, Locale.ENGLISH ) );
        error.setErrorDescription( message );
        return error;
    }

    @ExceptionHandler( ResourceNotFoundException.class )
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    public @ResponseBody ExceptionResponse handleResourceNotFoundException( final ResourceNotFoundException exception,
                                                                   final HttpServletRequest request )
    {

        String message = exception.getMessage();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorCode( messageSource.getMessage( "InvalidInput_code", null, Locale.ENGLISH ) );
        error.setErrorDescription( message );
        return error;
    }

    @ExceptionHandler( BadRequestException.class )
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    public @ResponseBody ExceptionResponse handleBadRequestException( final BadRequestException exception,
                                                                   final HttpServletRequest request )
    {
        String exceptionClassName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorCode(messageSource.getMessage( exceptionClassName + ApplicationConstant.CODE, null, Locale.ENGLISH ) );
        error.setErrorDescription( message );
        return error;
    }



}
