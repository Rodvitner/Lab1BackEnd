package com.viewmodels.requestviews;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class CreateUserRequest {

    private String name, email, password;

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

    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return name+";"+email+";"+password;
    }
}
