package com.iubh.boardgamer.Game;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iubh.boardgamer.Auth.User;

public class JoinUserBrettspiel {

    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("brettspiel")
    @Expose
    private Brettspiel brettspiel;
    @SerializedName("user")
    @Expose
    private User user;


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Brettspiel getBrettspiel() {
        return brettspiel;
    }

    public void setBrettspiel(Brettspiel brettspiel) {
        this.brettspiel = brettspiel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
