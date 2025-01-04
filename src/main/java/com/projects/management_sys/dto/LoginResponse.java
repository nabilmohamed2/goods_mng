package com.projects.management_sys.dto;

import java.util.Set;

public class LoginResponse {
    private String username;
    private Set<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public LoginResponse(String username, Set<String> role) {
        this.username = username;
        this.role = role;
    }

    public LoginResponse() {
    }
}
