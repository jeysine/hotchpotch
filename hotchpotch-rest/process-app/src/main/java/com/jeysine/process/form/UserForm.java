package com.jeysine.process.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author yaojx
 * @date 2018-10-09
 */
public class UserForm {
    /** 名字 */
    private String name;

    /** 性别，男，女 */
    private String sex;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 说明备注 */
    private String remark;

    /** 电话号码 */
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /** 身份证号 */
    private String idCard;

    private String nickname;

    private String category;

    private String relationBlind;

    private String staffId;

    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRelationBlind() {
        return relationBlind;
    }

    public void setRelationBlind(String relationBlind) {
        this.relationBlind = relationBlind;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", idCard='" + idCard + '\'' +
                ", nickname='" + nickname + '\'' +
                ", category='" + category + '\'' +
                ", relationBlind='" + relationBlind + '\'' +
                ", staffId='" + staffId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
