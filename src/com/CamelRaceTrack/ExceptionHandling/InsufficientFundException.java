package com.CamelRaceTrack.ExceptionHandling;

public class InsufficientFundException extends Throwable {
    public InsufficientFundException(String amount) {
        System.out.println(new StringBuilder().append("Insufficient Funds: ").append(amount));
    }
}
