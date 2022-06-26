package com.iubh.boardgamer.Auth;

public class User {

    public String fullName, email, uid;

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

    public String getUid(){return uid;}


    public void setUid(String uid) { this.uid = uid;}
    public void setGroup(String group){ this.group = group;}
    public void setEmail(String email) { this.email = email;}
    public void setFullName (String fullName){this.fullName = fullName;}
}
