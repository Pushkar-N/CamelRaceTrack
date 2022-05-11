package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.Controller.Controller;
import com.CamelRaceTrack.ExceptionHandling.InvalidCamelException;
import com.CamelRaceTrack.Interfaces.Command;
import org.apache.log4j.Logger;

public class winnerCommand implements Command {

    private static org.apache.log4j.Logger log = Logger.getLogger(Command.class);
    private int winningCamel;

    public winnerCommand(String userInput) {
        String[] data = userInput.split(Constants.SINGLE_SPACE);
        winningCamel = Integer.parseInt(data[1]);
    }

    @Override
    public void processRequest() throws InvalidCamelException {
        Controller.setWinningCamel(winningCamel);
    }
}
