package com.CamelRaceTrack.Controller;

import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.Models.Camel;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Initialize {

    private static org.apache.log4j.Logger log = Logger.getLogger(Initialize.class);

    protected static ArrayList<Camel> racecamels;
    protected static Map<Integer, Integer> inventory ;

    public static void Initialize() {
        log.info("Initialising application.");
        racecamels = new ArrayList<>();
        inventory = new LinkedHashMap<>();

        log.info("Initialising the Camel list.");
        racecamels.add(new Camel(1, Constants.THAT_DRAN_GRAY_CAT, 5, true));
        racecamels.add(new Camel(2,Constants.FORT_UTOPIA, 10, false));
        racecamels.add(new Camel(3,Constants.COUNT_SHEEP, 9, false));
        racecamels.add(new Camel(4,Constants.MS_TRAITOUR, 4, false));
        racecamels.add(new Camel(5,Constants.REAL_PRINCESS, 3, false));
        racecamels.add(new Camel(6,Constants.PA_KETTLE, 5, false));
        racecamels.add(new Camel(7,Constants.GIN_STRINGER, 6, false));

        log.info("Initialising the Inventory list.");
        inventory.put(100,10);
        inventory.put(20,10);
        inventory.put(10,10);
        inventory.put(5,10);
        inventory.put(1,10);
    }

    public static void DisplayCurrentApplicationStatus(){
        log.info("Displaying the Current Application status");

        System.out.println("Inventory:");
        inventory.forEach((key,value) -> System.out.println(MessageFormat.format("{0}{1},{2}",Constants.DOLLAR_SIGN,key,value)));

        System.out.println("Camels:");
        racecamels.forEach(camel -> System.out.println(MessageFormat.format("{0},{1},{2},{3}"
                , camel.getNumber(), camel.getName(), camel.getOdds(), camel.getDidwin().equals(true)?"won":"lost")));
    }

    public static int getTotalInventoryValue() {
        return inventory.entrySet().stream().mapToInt(value -> value.getKey()* value.getValue()).sum();
    }
}
