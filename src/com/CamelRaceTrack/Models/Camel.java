package com.CamelRaceTrack.Models;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    public static void DisplayAllCamels(ArrayList<Camel> racecamels) {
        log.info("Inside the DisplayAllCamels method.");
        System.out.println("Camels: ");
        for (Camel c: racecamels
             ) {
            System.out.println(MessageFormat.format("{0},{1},{2},{3}", c.getNumber(), c.getName(), c.getOdds(), c.getDidwin().equals(true)?"won":"lost"));
        }
        log.info("All Camels displayed successfully.");
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

    public static Camel findByCamelNumber(Collection<Camel> camelList, int camelNumber)
    {
        for (Camel c : camelList){

            if(c.getNumber().equals(camelNumber)){
                return c;
            }
        }
        return null;
    }

    public static void SetWinningCamel(int camelNumber, ArrayList<Camel> racecamels) {
        log.info("Inside the SetWinningCamel method");
        for (Camel racecamel : racecamels) {
            //setting the current winning camel property to false..
            if(racecamel.getNumber() != camelNumber && racecamel.getDidwin()) {
                log.info(MessageFormat.format("setting previous winning camel to FALSE. Camel No. : {1}, Camel Name : {0}", racecamel.getName(),racecamel.getNumber()));
                racecamel.setDidwin(false);
            }
            else if(racecamel.getNumber() == camelNumber){
                log.info(MessageFormat.format("setting current winning camel to TRUE. Camel No. : {1}, Camel Name : {0}", racecamel.getName(), racecamel.getNumber()));
                racecamel.setDidwin(true);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camel camel = (Camel) o;
        return Number.equals(camel.Number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Number);
    }
}
