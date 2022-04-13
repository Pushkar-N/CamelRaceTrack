package com.CamelRaceTrack.ExceptionHandling;

import java.text.MessageFormat;

public class NoPayoutException extends Throwable {
    public NoPayoutException(String camelName) {
        System.out.println(MessageFormat.format("No Payout: {0}",camelName));
    }
}
