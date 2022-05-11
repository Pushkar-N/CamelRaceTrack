package com.CamelRaceTrack.ExceptionHandling;

import java.text.MessageFormat;

public class InvalidCamelException extends Throwable {
    public InvalidCamelException(int camelNumber) {
        System.out.println(MessageFormat.format("Invalid Camel Number: {0}",camelNumber));
    }
}
