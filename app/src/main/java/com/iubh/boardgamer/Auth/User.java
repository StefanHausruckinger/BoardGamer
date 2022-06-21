package com.iubh.boardgamer.Auth;

public class User {

    public String fullName, email;

    public static String group;

    public User(){
    }

    public User(String fullName, String email, String group){
        this.fullName = fullName;
        this.email = email;
        this.group = group;
    }

    public String getFullName(){
        return fullName;
    }

    public String getEmail(){
        return email;
    }

    public String getGroup(){
        return group;
    }




}
