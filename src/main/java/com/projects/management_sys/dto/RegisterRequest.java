package com.projects.management_sys.dto;

import java.util.Set;

public class RegisterRequest {

    private String username;
    private String password;
    //A set of role names is passed along with the request Ex: User, Admin
    private Set<String> roles;

    public RegisterRequest() {}

    public RegisterRequest(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
