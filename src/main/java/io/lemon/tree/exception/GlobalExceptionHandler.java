package io.lemon.tree.exception;

import io.lemon.tree.common.LemonTreeResponse;
import io.lemon.tree.common.LemonTreeResponseEnum;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public LemonTreeResponse defaultExceptionHandler(Exception e) {
        LemonTreeResponse response = null;
        if (e instanceof NoHandlerFoundException) {
            response = LemonTreeResponse.construct(LemonTreeResponseEnum.HANDLER_NOT_FOUND);
            response.setMessage("please check the request path");
        } else if (e instanceof HttpMessageConversionException) {
            response = LemonTreeResponse.failure();
            response.setMessage("please check the request parameter's type");
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ee = (MethodArgumentNotValidException) e;
            StringBuilder message = new StringBuilder();
            message.append("please check the request parameter's format. ");
            for (FieldError fieldError : ee.getFieldErrors()) {
                message.append(fieldError.getDefaultMessage());
                message.append(";");
            }
            response = LemonTreeResponse.construct(LemonTreeResponseEnum.PARAM_ERROR);
            response.setMessage(message.toString());
        } else if (e instanceof BindException) {
            StringBuilder message = new StringBuilder();
            message.append("please check the request parameter's format. ");
            for (FieldError fieldError : ((BindException) e).getFieldErrors()) {
                message.append(fieldError.getDefaultMessage());
                message.append(";");
            }
            response = LemonTreeResponse.construct(LemonTreeResponseEnum.PARAM_ERROR);
            response.setMessage(message.toString());
        } else {
            response = LemonTreeResponse.construct(LemonTreeResponseEnum.INTERNAL_SERVER_ERROR);
            response.setMessage("unknown error for current request");
        }
        return response;
    }

}
