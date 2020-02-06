package com.example.famchat;

public class CurrentUser {
    private String Email;
    private String Name;

    public CurrentUser(){
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
