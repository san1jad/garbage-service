package com.garbage.exception;

import com.common.constant.error.ErrorCode;
import com.common.exception.global.HandledApiErrors;
import com.garbage.payload.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFound resourceNotFound) {
        String message = resourceNotFound.getMessage();

        ApiResponse apiResponse = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HandledApiErrors> handleInvalidArgumentException(MethodArgumentNotValidException ex) {

        List<String> collect = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            // errorMap.put(error.getField(), error.getDefaultMessage());
            collect.add(error.getDefaultMessage());
        });
        HandledApiErrors apiError = new HandledApiErrors(ErrorCode.GARBAGE_ERROR.toString(), collect);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
