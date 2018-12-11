package com.jeysine.services.user.entity;

import com.jeysine.services.common.entity.Base;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author yaojx
 * @date 2018-10-08
 */
@Alias("UserM")
public class User extends Base {
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

    /** 身份证号 */
    private String idCard;

    private Date birthday;

    private String nickname;

    private String category;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
}
