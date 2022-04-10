package com.CamelRaceTrack;

public class Commons {
    public static boolean tryParseInt(String value){
        try{
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }
}
