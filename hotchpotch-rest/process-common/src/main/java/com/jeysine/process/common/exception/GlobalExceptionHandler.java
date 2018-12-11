package com.jeysine.process.common.exception;

import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.common.exception.GlassesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yaojx
 * @date 2018-09-30
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GlassesException.class)
    @ResponseBody
    public ResponseVO handleGlassesException(GlassesException e) {
        logger.warn("WARN: ", e);
        return ResponseVO.error(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVO handleException(Exception e) {
        logger.error("SYSTEM ERROR: ", e);
        return ResponseVO.error();
    }
}
