package org.yfr.sample.item.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SampleItemControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity runtimeExceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}