package com.CamelRaceTrack.Controller;

import com.CamelRaceTrack.Common.Commons;
import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Models.Camel;
import com.CamelRaceTrack.Models.Inventory;
import com.CamelRaceTrack.Models.UserCommand;
import java.util.ArrayList;


public class Controller {

    private static ArrayList<Camel> racecamels ;
    private static ArrayList<Inventory> inventories ;

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

//        DisplayCurrentApplicationStatus();

    }

    public static void DisplayCurrentApplicationStatus(){
        Inventory.DisplayAllInventory(inventories);
        Camel.DisplayAllCamels(racecamels);
    }

    public static void ProcessRequest(UserCommand userCommand) throws InvalidCommandException, NoPayoutException, InsufficientFundException {
        switch (userCommand.getCommand()) {
            case Constants.WINNER -> {
                Camel.SetWinningCamel(userCommand.getCamelNumber(), racecamels);
//                DisplayCurrentApplicationStatus();
            }
            case Constants.BET -> {
                Camel betCamel = Camel.findByCamelNumber(racecamels, userCommand.getCamelNumber());
                if (betCamel.getDidwin().equals(true)) {
                    int totalBetAmount = betCamel.getOdds() * (int) userCommand.getBetAmount();
                    if (Inventory.CheckInventory(inventories, totalBetAmount)) {
                        System.out.println(new StringBuilder().append("Payout: ").append(betCamel.getName()).append(", ").append(Constants.DOLLAR_SIGN)
                                .append(totalBetAmount));
                        DispenseAmount(inventories, betCamel.getOdds() * (int) userCommand.getBetAmount());
                    }
                    else
                        throw new InsufficientFundException(Integer.toString(totalBetAmount));
                } else
                    throw new NoPayoutException(betCamel.getName());
//                DisplayCurrentApplicationStatus();
            }
            case Constants.RESTOCK -> {
                System.out.println("Restocked Inventory");
                InitializeApplication();
            }
            case Constants.QUIT -> System.exit(0);
            default -> throw new InvalidCommandException(userCommand.getUserInputCommand());
        }
    }

    public static void DispenseAmount(ArrayList<Inventory> inventories, int amount) {

        int[] notes = Inventory.GetAllAmounts(inventories);
        int[] noteCounter = new int[notes.length];

        for(int i =0; i<notes.length; i++){
            if(amount>=notes[i]){
                noteCounter[i] = (Math.min(amount / notes[i], inventories.get(i).getCount()));
                amount = amount - noteCounter[i] * notes[i];
            }
        }
        System.out.println("Dispensing:");
        for(int i = notes.length - 1; i>=0 ; i--){
            System.out.println(new StringBuilder().append(Constants.DOLLAR_SIGN).append(notes[i]).append(",").append(noteCounter[i]));
            inventories.get(i).setCount(inventories.get(i).getCount() - noteCounter[i]);
        }

//        Inventory.DisplayAllInventory(inventories);
    }

    public static Boolean ValidateRequest(UserCommand userCommand) throws InvalidBetException, InvalidCamelException, InvalidCommandException {
        switch(userCommand.getCommand()) {
            case Constants.BET:
                if(userCommand.getBetAmount() <= Constants.DEFAULT || !Commons.tryParseInt(Float.toString(userCommand.getBetAmount())))
                    throw new InvalidBetException(Float.toString(userCommand.getBetAmount()));
            case Constants.WINNER:
                if(Camel.findByCamelNumber(racecamels, userCommand.getCamelNumber()) == null)
                    throw new InvalidCamelException((userCommand.getCamelNumber()));
                else
                    return true;
            case Constants.RESTOCK:
            case Constants.QUIT:
                if( userCommand.getCamelNumber() != Constants.DEFAULT)
                    throw new InvalidCommandException(userCommand.getUserInputCommand());
                else
                    return true;
            default:
                throw new InvalidCommandException(userCommand.getUserInputCommand());
        }
    }

    public static UserCommand ParseInputData(String userInput) throws InvalidCommandException {
        UserCommand userCommand = new UserCommand(userInput);
        try {

//            System.out.println("User Input is :" + userInput);

            String[] data = userInput.split(Constants.SINGLE_SPACE);
//            for (String element : data
//            ) {
//                System.out.println(element);
//            }

            //checking for a bet...
            if(Commons.tryParseInt(data[0]))
            {
                userCommand.setCamelNumber(Integer.parseInt(data[0]));
                userCommand.setCommand(Constants.BET);
                userCommand.setBetAmount(Float.parseFloat(data[1]));
            }
            else {
                //checking if the first input has multiple characters. Eg w2 10, q2
                if(data[0].toCharArray().length > 1)
                    throw new InvalidCommandException(userInput);
                else
                    userCommand.setCommand(data[0].toLowerCase().toCharArray()[0]);

                //Checking for the second input..
                if(data.length >1)
                    userCommand.setCamelNumber(Integer.parseInt(data[1]));
            }

            return userCommand;
        } catch (Exception ex) {
            throw new InvalidCommandException(userInput);
        }
    }


}
