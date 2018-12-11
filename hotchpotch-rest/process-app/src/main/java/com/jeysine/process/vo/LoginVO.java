package com.jeysine.process.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author yaojx
 * @date 2018-10-09
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginVO {
    /** 网易云通信ID可以指定登录token值 */
    private String token;

    /** 名称 */
    private String name;

    /** 帐号 */
    private String accid;

    /** 客服编号 */
    private String staffId;

    /** 关联的视障人士用户ID,如关联多个用户，可通过’,’分割 */
    private String relationBlind;

    private String nickname;

    private String username;

    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @JsonProperty("registerTime")
    private Date createTime;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date birthday;

    private String idCard;

    private String remark;

    private String language;

    private String ttTravelToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRelationBlind() {
        return relationBlind;
    }

    public void setRelationBlind(String relationBlind) {
        this.relationBlind = relationBlind;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTtTravelToken() {
        return ttTravelToken;
    }

    public void setTtTravelToken(String ttTravelToken) {
        this.ttTravelToken = ttTravelToken;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", accid='" + accid + '\'' +
                ", staffId='" + staffId + '\'' +
                ", relationBlind='" + relationBlind + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", category='" + category + '\'' +
                ", createTime=" + createTime +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", idCard='" + idCard + '\'' +
                ", remark='" + remark + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
