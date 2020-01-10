package com.example.famchat;

public class Users {
    String Email;
    String Password;
    String Name;

    public Users(String Email, String Password, String Name){
        this.Email = Email;
        this.Password = Password;
        this.Name = Name;
    }

    public String getEmail(){
        return Email;
    }

    public String getPassword(){
        return Password;
    }

    public String getName(){
        return Name;
    }
}

