package com.iubh.boardgamer.Bewertung;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iubh.boardgamer.Auth.User;
import com.iubh.boardgamer.Game.Spieltermin;

public class Bewertung {
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("userId")
    @Expose
    private User userId;
    @SerializedName("spielterminId")
    @Expose
    private Spieltermin spielterminId;
    @SerializedName("hostRating")
    @Expose
    private int hostRating;
    @SerializedName("gamesRating")
    @Expose
    private int gamesRating;
    @SerializedName("foodRating")
    @Expose
    private int foodRating;
    @SerializedName("eventRating")
    @Expose
    private int eventRating;


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Spieltermin getSpielterminId() {
        return spielterminId;
    }

    public void setSpielterminId(Spieltermin spielterminId) {
        this.spielterminId = spielterminId;
    }

    public int getHostRating() {
        return hostRating;
    }

    public void setHostRating(int hostRating) {
        this.hostRating = hostRating;
    }

    public int getGamesRating() {
        return gamesRating;
    }

    public void setGamesRating(int gamesRating) {
        this.gamesRating = gamesRating;
    }

    public int getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(int foodRating) {
        this.foodRating = foodRating;
    }

    public int getEventRating() {
        return eventRating;
    }

    public void setEventRating(int eventRating) {
        this.eventRating = eventRating;
    }
}
