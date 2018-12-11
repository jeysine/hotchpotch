package com.jeysine.services.common.exception;

import com.jeysine.services.common.constants.ApiCode;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public class GlassesException extends Exception {
    private ApiCode errorCode;

    public GlassesException() {
    }
    public GlassesException(ApiCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GlassesException(ApiCode errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
    }
    public GlassesException(ApiCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GlassesException(String message) {
        super(message);
    }

    public GlassesException(Throwable cause) {
        super(cause);
    }

    public GlassesException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlassesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ApiCode errorCode) {
        this.errorCode = errorCode;
    }
}
