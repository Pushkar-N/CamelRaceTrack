package com.CamelRaceTrack.ExceptionHandling;

import java.text.MessageFormat;

public class InsufficientFundException extends Throwable {
    public InsufficientFundException(String amount) {
        System.out.println(MessageFormat.format("Insufficient Funds: {0}",amount));
    }
}
