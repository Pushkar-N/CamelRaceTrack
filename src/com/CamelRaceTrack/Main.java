package com.CamelRaceTrack;

import com.CamelRaceTrack.Common.Commons;
import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Models.Camel;
import com.CamelRaceTrack.Models.Inventory;
import com.CamelRaceTrack.Models.UserCommand;

import java.util.ArrayList;
import java.util.Scanner;

import static com.CamelRaceTrack.Controller.Controller.*;

public class Main {

    public static ArrayList<Camel> racecamels ;
    public static ArrayList<Inventory> inventories ;

    public static void main(String[] args) {
	// write your code here
        InitializeApplication();

        while(true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            try {

                UserCommand userCommand = ParseInputData(userInput);
                if(ValidateRequest(userCommand))
                    ProcessRequest(userCommand);

            } catch (Exception | InvalidBetException | InvalidCamelException | InvalidCommandException | NoPayoutException | InsufficientFundException ex) {
                if(ex instanceof InvalidCamelException || ex instanceof  InvalidBetException || ex instanceof InvalidCommandException
                        || ex instanceof NoPayoutException || ex instanceof InsufficientFundException)
                    continue;
                else
                    System.out.println("Exception in application :" + ex.getMessage());
            }
        }

    }

    public static void InitializeApplication() {
        racecamels = new ArrayList<>();

        racecamels.add(new Camel(1,Constants.THAT_DRAN_GRAY_CAT, 5, true));
        racecamels.add(new Camel(2,Constants.FORT_UTOPIA, 10, false));
        racecamels.add(new Camel(3,Constants.COUNT_SHEEP, 9, false));
        racecamels.add(new Camel(4,Constants.MS_TRAITOUR, 4, false));
        racecamels.add(new Camel(5,Constants.REAL_PRINCESS, 3, false));
        racecamels.add(new Camel(6,Constants.PA_KETTLE, 5, false));
        racecamels.add(new Camel(7,Constants.GIN_STRINGER, 6, false));

        inventories = new ArrayList<>();

        inventories.add(new Inventory(100,10));
        inventories.add(new Inventory(20,10));
        inventories.add(new Inventory(10,10));
        inventories.add(new Inventory(5,10));
        inventories.add(new Inventory(1,10));

        Camel.DisplayAllCamels(racecamels);
        Inventory.DisplayAllInventory(inventories);
    }
}
