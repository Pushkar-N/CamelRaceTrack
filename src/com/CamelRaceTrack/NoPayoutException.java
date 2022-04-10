package com.CamelRaceTrack;

public class NoPayoutException extends Throwable {
    public NoPayoutException(String camelName) {
        System.out.println(new StringBuilder().append("No Payout:").append(camelName).toString());
    }
}
