package com.mtt.controller;

public class LoginForm {

    private String username;

    private String password;

    private boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof LoginForm)) { return false; }

        LoginForm loginForm = (LoginForm) o;

        if (password != null ? !password.equals(loginForm.password) : loginForm.password != null) { return false; }
        if (username != null ? !username.equals(loginForm.username) : loginForm.username != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
