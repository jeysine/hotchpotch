package com.jeysine.process.form;

/**
 * @author yaojx
 * @date 2018-10-09
 */
public class LoginForm {
    private String username;

    private String password;

    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
