package com.jeysine.process.common.vo;

/**
 * @author yaojx
 * @date 2018-10-08
 */
public class Header {
    /**
     * 状态码
     */
    private String status;

    /** 结果信息 */
    private String resultMsg;

    public Header() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
