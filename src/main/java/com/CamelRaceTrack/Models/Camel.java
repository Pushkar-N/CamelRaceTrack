package com.CamelRaceTrack.Models;

import org.apache.log4j.Logger;

public class Camel {

    private static final org.apache.log4j.Logger log = Logger.getLogger(Camel.class);

    private final int number;
    private final String name;
    private final int odds;
    private boolean didwin;

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Integer getOdds() {
        return odds;
    }

    public Boolean getDidwin() {
        return didwin;
    }

    public void setDidwin(boolean didwin) {
        this.didwin = didwin;
    }

    public Camel(int number, String name, int odds, boolean didwin)
    {
        this.number = number;
        this.name = name;
        this.didwin = didwin;
        this.odds = odds;
    }
}
