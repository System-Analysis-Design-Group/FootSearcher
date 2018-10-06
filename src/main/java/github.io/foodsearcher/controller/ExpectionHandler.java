package top.yaoyongdou.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yaoyongdou.model.StatusMsg;

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
