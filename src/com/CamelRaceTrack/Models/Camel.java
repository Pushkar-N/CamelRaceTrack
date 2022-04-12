package com.CamelRaceTrack.Models;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Camel {
    private Integer Number;
    private String Name;
    private Integer Odds;
    private Boolean Didwin;

    public static void DisplayAllCamels(ArrayList<Camel> racecamels) {
        System.out.println("Camels: ");
        for (Camel c: racecamels
             ) {
            System.out.println(MessageFormat.format("{0},{1},{2},{3}", c.getNumber(), c.getName(), c.getOdds(), c.getDidwin().equals(true)?"won":"lost"));
        }
    }

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

    public void setName(String name) {
        Name = name;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public void setOdds(Integer odds) {
        Odds = odds;
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

    public static Camel getWinningCamel(Collection<Camel> camelList)
    {
        for (Camel c : camelList){
            if(c.getDidwin().equals(true)){
                return c;
            }
        }
        return null;
    }

    public static void SetWinningCamel(int camelNumber, ArrayList<Camel> racecamels) {
        for (Camel racecamel : racecamels) {
            //setting the current winning camel property to false..
            if(racecamel.getNumber() != camelNumber && racecamel.getDidwin())
                racecamel.setDidwin(false);
            else if(racecamel.getNumber() == camelNumber){
                racecamel.setDidwin(true);
                Camel.DisplayAllCamels(racecamels);
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
