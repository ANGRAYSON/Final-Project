package com.hvacparts.parts.errorhandler;

import java.util.NoSuchElementException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
  
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public String handleNoSuchElementException (NoSuchElementException e, WebRequest webRequest) {
    return(e.toString());
  }
  
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public String handle400(Exception e, WebRequest webRequest) {
    log.error("Invalid request: {}",e.toString());
    return(e.toString());
  }
  
  
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public String handleMethodArguementTypeMismatchException(Exception e, WebRequest webRequest) {
    return(e.toString());
  }
  
}
