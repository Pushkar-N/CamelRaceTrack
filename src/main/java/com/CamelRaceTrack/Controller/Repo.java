package com.CamelRaceTrack.Controller;

import com.CamelRaceTrack.Models.Camel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Repo {

    private static org.apache.log4j.Logger log = Logger.getLogger(Repo.class);

    protected static ArrayList<Camel> racecamels;
    protected static LinkedHashMap<Integer, Integer> inventory ;

    public void setInventory(LinkedHashMap<Integer, Integer> inventory) {
        log.info("Initialising inventory");
        this.inventory = inventory;
    }

    public void setRacecamels(ArrayList<Camel> racecamels) {
        log.info("Initialising race camels");
        this.racecamels = racecamels;
    }

}
