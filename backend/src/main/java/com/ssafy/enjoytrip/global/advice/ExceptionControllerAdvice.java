package com.ssafy.enjoytrip.global.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssafy.enjoytrip.global.BusinessException;
import com.ssafy.enjoytrip.global.code.ErrorInfo;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unExpectedErrorHandler(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("유효하지 않은 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorInfo> loginFailHandler(BusinessException e) {
        return new ResponseEntity<ErrorInfo>(e.getErrorInfo(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
