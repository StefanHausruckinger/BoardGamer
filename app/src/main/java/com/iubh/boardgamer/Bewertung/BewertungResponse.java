package com.iubh.boardgamer.Bewertung;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BewertungResponse {
    @SerializedName("results")
    @Expose
    private Bewertung[] results;

    public Bewertung[] getResults() {
        return results;
    }

    public void setResults(Bewertung[] results) {
        this.results = results;
    }
}
