package com.CamelRaceTrack.ExceptionHandling;

public class InvalidCommandException extends Throwable {
    public InvalidCommandException(String userInput) {
        System.out.println(new StringBuilder().append("Invalid command: ").append(userInput));
    }
}
