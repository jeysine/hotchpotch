package com.jeysine.process.exception;


import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.common.constants.ApiCode;
import com.jeysine.services.common.exception.GlassesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yaojx
 * @date 2018-10-18
 */
@ControllerAdvice
public class GlassesControllerAdvice {

    private final static Logger logger = LoggerFactory.getLogger(GlassesControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = GlassesException.class)
    public ResponseVO glassesExceptionHandler(GlassesException ex) {
        logger.error("请求异常, 错误码: {}, 错误信息: {}",ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage());

        return ResponseVO.error(ex.getErrorCode());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVO generateExceptionHandler(Exception ex) {
        logger.error("系统异常: {}", ex);

        return ResponseVO.error(ApiCode.UNKNOWN_ERROR);
    }
}
