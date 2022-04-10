package com.CamelRaceTrack;

public class InvalidCommandException extends Throwable {
    public InvalidCommandException(String userInput) {
        System.out.println(new StringBuilder().append("Invalid command: ").append(userInput).toString());
    }
}
