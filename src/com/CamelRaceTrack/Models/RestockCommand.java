package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Controller.Controller;
import com.CamelRaceTrack.Interfaces.Command;

public class RestockCommand implements Command {

    @Override
    public void processRequest() {
        System.out.println("Restocked Inventory");
        Controller.Initialize();
    }
}
