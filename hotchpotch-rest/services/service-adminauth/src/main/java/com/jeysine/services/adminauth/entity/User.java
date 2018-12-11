package com.jeysine.services.adminauth.entity;

import com.jeysine.services.common.entity.Base;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("UserM")
public class User extends Base{
    private String name;

    private String password;

    private Integer sex;

    private String account;

    private String dept;

    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
