package org.eyeseetea.connect2connectexampleapp.model;

public class auth {
    private String userName;
    private String password;

    public auth() {
    }

    public auth(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
