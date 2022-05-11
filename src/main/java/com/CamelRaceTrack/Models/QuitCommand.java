package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Interfaces.Command;

public class QuitCommand implements Command {

    @Override
    public void processRequest() {
        System.exit(0);
    }
}
