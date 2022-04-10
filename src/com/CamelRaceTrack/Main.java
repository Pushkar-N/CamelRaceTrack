package com.CamelRaceTrack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Camel> racecamels ;
    private static ArrayList<Inventory> inventories ;

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

            } catch (Exception | InvalidBetException | InvalidCamelException | InvalidCommandException | NoPayoutException ex) {
                if(ex instanceof InvalidCamelException || ex instanceof  InvalidBetException || ex instanceof InvalidCommandException || ex instanceof NoPayoutException)
                    continue;
                else
                    System.out.println("Exception in application :" + ex.getMessage());
            }
        }

    }

    private static void ProcessRequest(UserCommand userCommand) throws InvalidCommandException, NoPayoutException {
        switch (userCommand.getCommand()) {
            case 'w' -> Camel.SetWinningCamel(userCommand.CamelNumber, racecamels);
            case 'b' -> {
                Camel betCamel = Camel.findByCamelNumber(racecamels, userCommand.CamelNumber);
                if (betCamel.getDidwin().equals(true)) {
                    if (Inventory.CheckInventory(inventories, betCamel.getOdds() * (int) userCommand.getBetAmount())) {
                        System.out.println(new StringBuilder().append("Payout: ").append(betCamel.getName()).append(", ").append(Constants.DOLLAR_SIGN)
                                .append(betCamel.getOdds() * (int) userCommand.getBetAmount()));
                        DispenseAmount(inventories, betCamel.getOdds() * (int) userCommand.getBetAmount());
                    }
                } else
                    throw new NoPayoutException(betCamel.getName());
            }
            case 'r' -> {
                System.out.println("Restocked Inventory");
                InitializeApplication();
            }
            case 'q' -> System.exit(0);
            default -> throw new InvalidCommandException(userCommand.getUserInputCommand());
        }
    }

    private static void DispenseAmount(ArrayList<Inventory> inventories, int amount) {

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
            inventories.get(i).Count -= noteCounter[i];
        }

//        Inventory.DisplayAllInventory(inventories);
    }

    private static Boolean ValidateRequest(UserCommand userCommand) throws InvalidBetException, InvalidCamelException, InvalidCommandException {
        switch(userCommand.getCommand()) {
            case 'b':
                if(userCommand.getBetAmount() <= Constants.DEFAULT)
                    throw new InvalidBetException(Float.toString(userCommand.getBetAmount()));
            case 'w':
                if(Camel.findByCamelNumber(racecamels, userCommand.getCamelNumber()) == null)
                    throw new InvalidCamelException((userCommand.getCamelNumber()));
                else
                    return true;
            case 'r':
            case 'q':
                if( userCommand.getCamelNumber() != Constants.DEFAULT)
                    throw new InvalidCommandException(userCommand.UserInputCommand);
                else
                return true;
            default:
                throw new InvalidCommandException(userCommand.getUserInputCommand());
        }
    }

    private static UserCommand ParseInputData(String userInput) throws InvalidCommandException {
        UserCommand userCommand = new UserCommand(userInput);
        try {

            System.out.println("User Input is :" + userInput);

            String[] data = userInput.split(Constants.SINGLE_SPACE);
//            for (String element : data
//            ) {
//                System.out.println(element);
//            }

            //checking for a bet...
            if(Commons.tryParseInt(data[0]))
            {
                userCommand.CamelNumber = Integer.parseInt(data[0]);
                userCommand.Command = Constants.BET;
                userCommand.BetAmount = Float.parseFloat(data[1]);
            }
            else {
                //checking if the first input has multiple characters. Eg w2 10, q2
                if(data[0].toCharArray().length > 1)
                    throw new InvalidCommandException(userInput);
                else
                    userCommand.Command = data[0].toLowerCase().toCharArray()[0];

                //Checking for the second input..
                if(data.length >1)
                    userCommand.CamelNumber = Integer.parseInt(data[1]);
            }

//            System.out.println("UserInputCommand :" + userCommand.getUserInputCommand()
//                    + ", Command :" + userCommand.getCommand()
//                    + ", Camel Number :" + userCommand.getCamelNumber()
//                    + ", BetAmount :" + userCommand.getBetAmount()
//            );

            return userCommand;
        } catch (Exception ex) {
            throw new InvalidCommandException(userInput);
        }
    }


    private static void InitializeApplication() {
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
