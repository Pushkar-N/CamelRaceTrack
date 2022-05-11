package com.CamelRaceTrack.ExceptionHandling;

import java.text.MessageFormat;

public class InvalidCommandException extends Throwable {
    public InvalidCommandException(String userInput) {
        System.out.println(MessageFormat.format("Invalid command: {0}",userInput));
    }
}
