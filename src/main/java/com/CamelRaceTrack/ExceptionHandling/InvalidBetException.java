package com.CamelRaceTrack.ExceptionHandling;

import java.text.MessageFormat;

public class InvalidBetException extends Throwable {
    public InvalidBetException(String betAmount) {
        System.out.println(MessageFormat.format("Invalid Bet: {0}",betAmount));
    }
}
