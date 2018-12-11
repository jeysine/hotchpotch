package com.jeysine.services.user.exception;

import com.jeysine.services.common.constants.ApiCode;

/**
 * @author yaojx
 * @date 2018-10-09
 */
public class GlassesUserException extends Exception {
    private ApiCode errorCode;

    public GlassesUserException() {
    }

    public GlassesUserException(ApiCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GlassesUserException(ApiCode errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
    }
    public GlassesUserException(ApiCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GlassesUserException(String message) {
        super(message);
    }

    public GlassesUserException(Throwable cause) {
        super(cause);
    }

    public GlassesUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlassesUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ApiCode errorCode) {
        this.errorCode = errorCode;
    }
}
