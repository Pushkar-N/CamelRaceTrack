package com.CamelRaceTrack.Interfaces;

import com.CamelRaceTrack.ExceptionHandling.InsufficientFundException;
import com.CamelRaceTrack.ExceptionHandling.InvalidCamelException;
import com.CamelRaceTrack.ExceptionHandling.InvalidCommandException;
import com.CamelRaceTrack.ExceptionHandling.NoPayoutException;

public interface Command {

    void processRequest() throws InvalidCamelException, InvalidCommandException, NoPayoutException, InsufficientFundException;
}
