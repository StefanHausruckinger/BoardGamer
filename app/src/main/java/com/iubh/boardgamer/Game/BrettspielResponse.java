package com.iubh.boardgamer.Game;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrettspielResponse {
    @SerializedName("results")
    @Expose
    private Brettspiel[] results;

    public Brettspiel[] getResults() {
        return results;
    }
}
