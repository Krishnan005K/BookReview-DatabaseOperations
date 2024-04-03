package com.krishnan.bookreview.services;

public class UpdateRequest {
    private String name;
    private String email;
    private String password;
    public UpdateRequest(String name, String email, String password, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }
    private String mobile;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    
}
