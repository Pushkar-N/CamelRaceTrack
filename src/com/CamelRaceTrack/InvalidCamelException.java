package com.CamelRaceTrack;

public class InvalidCamelException extends Throwable {
    public InvalidCamelException(int camelNumber) {
        System.out.println(new StringBuilder().append("Invalid Camel Number: ").append(camelNumber).toString());
    }
}
