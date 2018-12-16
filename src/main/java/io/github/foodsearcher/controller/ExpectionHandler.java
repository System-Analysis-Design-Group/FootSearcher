package io.github.foodsearcher.controller;

import io.github.foodsearcher.model.StatusMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by young on 18-3-16.
 */

@ControllerAdvice
public class ExpectionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public StatusMsg errorHandler(Exception ex) {
        System.out.println(".............error...............");
        ex.printStackTrace();
        return StatusMsg.returnError();
    }
}
