package com.jeysine.services.common.constants;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public enum ApiCode {
    /**
     * 没有权限访问, 请先登录或联系管理员
     */
    STATUS_NOT_AUTH("001", "没有权限访问, 请先登录或联系管理员")

    /**
     * 成功
     */
    , STATUS_SUCCESS("0","成功")
    /**
     * 没数据
     */
    , NO_DATA("003","没数据")
    /**
     * 未知错误
     */
    , UNKNOWN_ERROR("999","未知错误")
    /**
     * 用户已存在
     */
    , USER_IS_EXIST("10001","用户已存在")
    /**
     * 账号或密码错误
     */
    , PASSWORD_ERROR("10003","账号或密码错误")
    /**
     * 用户没有登录
     */
    , USER_IS_NOT_LOGIN("10004","用户没有登录")
    /**
     * 找不到此用户
     */
    , USER_IS_NOT_FIND("10005","找不到此用户")
    /**
     * 参数错误
     */
    , PARAM_ERROR("20000","参数错误")
    /**
     * 用户名或密码错误
     */
    , USER_ACCOUNT_PASSWORD_ERROR("10008","用户名或密码错误")
    /**
     * 用户未激活
     */
    , USER_NOT_ACTIVE("10009","用户未激活")
    /**
     * 用户已被冻结
     */
    , USER_FROZEN("10010","用户已被冻结")
    ;

    private String status;

    private String message;

    ApiCode(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
