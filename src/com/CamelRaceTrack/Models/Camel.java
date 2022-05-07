package com.CamelRaceTrack.Models;

import org.apache.log4j.Logger;

public class Camel {

    private static final org.apache.log4j.Logger log = Logger.getLogger(Camel.class);

    private final Integer Number;
    private final String Name;
    private final Integer Odds;
    private Boolean Didwin;

    public Boolean getDidwin() {
        return Didwin;
    }
    public Integer getNumber() {
        return Number;
    }
    public Integer getOdds() {
        return Odds;
    }
    public String getName() {
        return Name;
    }

    public void setDidwin(Boolean didwin) {
        Didwin = didwin;
    }

    public Camel(Integer number, String name, Integer odds, Boolean didwin)
    {
        this.Number = number;
        this.Name = name;
        this.Didwin = didwin;
        this.Odds = odds;
    }
}
