package com.CamelRaceTrack.ExceptionHandling;

public class InvalidBetException extends Throwable {
    public InvalidBetException(String s) {
        System.out.println(new StringBuilder().append("Invalid Bet:").append(s));
    }
}
