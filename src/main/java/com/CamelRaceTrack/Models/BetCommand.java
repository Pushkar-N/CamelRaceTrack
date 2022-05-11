package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.Controller.Controller;
import com.CamelRaceTrack.ExceptionHandling.InsufficientFundException;
import com.CamelRaceTrack.ExceptionHandling.InvalidCommandException;
import com.CamelRaceTrack.ExceptionHandling.NoPayoutException;
import com.CamelRaceTrack.Interfaces.Command;

public class BetCommand implements Command {

    private int camelNumber;
    private int betAmount;

    public BetCommand(String userInput) {
        String[] data = userInput.split(Constants.SINGLE_SPACE);
        camelNumber = Integer.parseInt(data[0]);
        betAmount = Integer.parseInt(data[1]);
    }

    @Override
    public void processRequest() throws InvalidCommandException, NoPayoutException, InsufficientFundException {
        Controller.processBet(camelNumber, betAmount);
    }
}
