package com.bsilva.starwars.controller;

import com.bsilva.starwars.Json.ResponseJson;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerException  {
    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<ResponseJson> handleNotFoundException(NotFoundException ex, WebRequest request){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setDescription(ex.getMessage());
        responseJson.setId(request.getParameter("id"));
        responseJson.setError_code("002");
        return new ResponseEntity<ResponseJson>(responseJson, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ResponseJson> handleIllegarArgumentException(IllegalArgumentException ex, WebRequest request){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setDescription(ex.getMessage());
        responseJson.setId(request.getParameter("id"));
        responseJson.setError_code("001");
        return new ResponseEntity<ResponseJson>(responseJson, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
