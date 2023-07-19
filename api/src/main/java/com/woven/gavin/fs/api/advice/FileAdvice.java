package com.woven.gavin.fs.api.advice;

import com.woven.gavin.domain.exception.FileExistException;
import com.woven.gavin.domain.exception.FileNotExistException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FileAdvice {

    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    String handleSizeLimitExceeded(SizeLimitExceededException ex) {
        if (ex.getCause() == null) {
            return ex.getMessage();
        }
        if (ex.getCause().getCause() == null) {
            return ex.getCause().getMessage();
        }
        return ex.getCause().getCause().getMessage();
    }

    @ExceptionHandler({FileNotExistException.class, FileExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    String handleFileNotExist(RuntimeException ex) {
        return ex.getMessage();
    }
}
