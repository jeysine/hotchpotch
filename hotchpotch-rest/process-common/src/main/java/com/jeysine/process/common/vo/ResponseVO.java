package com.jeysine.process.common.vo;


import com.jeysine.services.common.constants.ApiCode;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public class ResponseVO<T> {
    private T data;

    private Header header ;

    public ResponseVO() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "data=" + data +
                ", header=" + header +
                '}';
    }

    public ResponseVO<T> success(T data) {
        ResponseVO<T> response = new ResponseVO<>();
        response.setData(data);
        success(response);
        return response;
    }

    public static ResponseVO success(ResponseVO response) {
        Header header = new Header();
        header.setStatus(ApiCode.STATUS_SUCCESS.getStatus());
        header.setResultMsg(ApiCode.STATUS_SUCCESS.getMessage());
        response.setHeader(header);
        return response;
    }

    public static ResponseVO success() {
        ResponseVO response = new ResponseVO();
        success(response);
        return response;
    }

    public static ResponseVO error(String status, String message) {
        ResponseVO response = new ResponseVO();
        Header header = new Header();
        header.setStatus(status);
        header.setResultMsg(message);
        response.setHeader(header);
        return response;
    }

    public static ResponseVO error(ApiCode apiCode) {
        ResponseVO response = new ResponseVO();
        Header header = new Header();
        header.setStatus(apiCode.getStatus());
        header.setResultMsg(apiCode.getMessage());
        response.setHeader(header);
        return response;
    }

    public static ResponseVO error() {
        ResponseVO response = new ResponseVO();
        Header header = new Header();
        header.setStatus(ApiCode.UNKNOWN_ERROR.getStatus());
        header.setResultMsg(ApiCode.UNKNOWN_ERROR.getMessage());
        return response;
    }

    public static ResponseVO noLogin() {
        ResponseVO response = new ResponseVO();
        Header header = new Header();
        header.setStatus(ApiCode.STATUS_NOT_AUTH.getStatus());
        header.setResultMsg(ApiCode.STATUS_NOT_AUTH.getMessage());
        response.setHeader(header);
        return response;
    }

}
