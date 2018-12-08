package org.domain.utils;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<MyError> defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MyError(e.getMessage()));
    }

    @Data
    private class MyError {

        @NonNull
        private String error;
    }
}
