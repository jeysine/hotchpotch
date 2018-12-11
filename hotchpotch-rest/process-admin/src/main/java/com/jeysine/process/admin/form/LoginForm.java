package com.jeysine.process.admin.form;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author yaojx
 * @date 2018-10-10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginForm {
    private String username;

    private String password;

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
}
