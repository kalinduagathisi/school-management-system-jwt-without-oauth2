package com.example.exceptions;

import com.example.dto.responseDto.ErrorMsgResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    // in build
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error-> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
        );
        return errorMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>("Validation failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // custom
    @ExceptionHandler(value = {StudentException.class})
    ResponseEntity<ErrorMsgResponseDto> handleStudentException(StudentException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMsgResponseDto(false, ex.getStatus(), ex.getMsg()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {PaymentSchemeException.class})
    ResponseEntity<ErrorMsgResponseDto> handlePaymentSchemeException(PaymentSchemeException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMsgResponseDto(false, ex.getStatus(), ex.getMsg()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {UserException.class})
    ResponseEntity<ErrorMsgResponseDto> handlePaymentSchemeException(UserException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMsgResponseDto(false, ex.getStatus(), ex.getMsg()), HttpStatus.OK);
    }

}
