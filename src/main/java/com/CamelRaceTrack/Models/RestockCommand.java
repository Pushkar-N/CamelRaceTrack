package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Controller.Controller;
import com.CamelRaceTrack.Interfaces.Command;
import com.CamelRaceTrack.Main;

public class RestockCommand implements Command {

    @Override
    public void processRequest() {
//        Controller controller = new Controller();
        Main.main(new String[]{});
    }
}
