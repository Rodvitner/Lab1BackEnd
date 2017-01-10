package com.viewmodels.generalviews;

/**
 * Created by archer on 2016-11-22.
 */
public class UserView {
    String email,name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserView(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UserView() {
    }

    @Override
    public String toString() {
        return "UserView{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
