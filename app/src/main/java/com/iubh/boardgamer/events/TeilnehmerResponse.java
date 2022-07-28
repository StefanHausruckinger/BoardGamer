package com.iubh.boardgamer.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeilnehmerResponse {
    @SerializedName("results")
    @Expose
    private Teilnehmer[] results;

    public Teilnehmer[] getResults() {
        return results;
    }

    public void setResults(Teilnehmer[] results) {
        this.results = results;
    }


}
