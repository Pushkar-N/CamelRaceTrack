package com.CamelRaceTrack.Controller;

import com.CamelRaceTrack.Common.Constants;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Interfaces.Command;
import com.CamelRaceTrack.Models.*;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Pattern;


public class Controller extends Repo {

    private static org.apache.log4j.Logger log = Logger.getLogger(Controller.class);

    public Command parseInput(String userInput) throws InvalidCommandException, InvalidBetException {

        //checking if the user input is empty string.
        if (!userInput.trim().isEmpty())
            if(Pattern.matches(Constants.WINNER_REGEX,userInput)) {
                return new winnerCommand(userInput);
            } else if(Pattern.matches(Constants.QUIT_REGEX,userInput)) {
                return new QuitCommand();
            } else if(Pattern.matches(Constants.RESTOCK_REGEX,userInput)) {
                return new RestockCommand();
            } else if(Pattern.matches(Constants.BET_REGEX,userInput)) {
                return new BetCommand(userInput);
            } else
                throw new InvalidCommandException(userInput);
        return null;
    }

    public static void processBet(int camelNumber, int betAmount) throws NoPayoutException, InsufficientFundException {

        Camel betCamel = racecamels.stream().filter(camel -> camel.getNumber().equals(camelNumber)).findFirst().orElse(null);
        if (betCamel.getDidwin().equals(true)) {
            int totalBetAmount = betCamel.getOdds() * betAmount;
            if ( getTotalInventoryValue()>= totalBetAmount) {
                System.out.println(MessageFormat.format("Payout: {0},{1}{2}",betCamel.getName(),Constants.DOLLAR_SIGN, totalBetAmount));
                DispenseAmount(totalBetAmount);
            }
            else
                throw new InsufficientFundException(Integer.toString(totalBetAmount));
        } else
            throw new NoPayoutException(betCamel.getName());
    }

    private static void DispenseAmount(int amount) {
        System.out.println("Dispensing:");

        Set<Integer> set = inventory.keySet();
        Iterator<Integer> itr = set.iterator();

        while (itr.hasNext()) {
            int noteCounter ;

            Integer key = Integer.parseInt(String.valueOf(itr.next())) ;
            log.info(MessageFormat.format("Current value of inventory >> Currency : {0} , Count : " ,key, inventory.get(key)));

            noteCounter = amount / key;
            System.out.println(MessageFormat.format("{0}{1},{2}", Constants.DOLLAR_SIGN,key,noteCounter));
            amount = amount - noteCounter * key;

            inventory.put(key,inventory.get(key) - noteCounter); //getting existing number of notes and subtracting the count.
            log.info(MessageFormat.format("Updated value of inventory >> Currency : {0} , Count : " ,key, inventory.get(key)));
        }
    }

    private static int getTotalInventoryValue() {
        return inventory.entrySet().stream().mapToInt(value -> Integer.parseInt(String.valueOf(value.getKey())) * value.getValue()).sum();
    }

    public static void setWinningCamel(int winningCamel) throws InvalidCamelException {

        //checking if the passed camel number is valid..
        if(!racecamels.stream().anyMatch(camel -> camel.getNumber().equals(winningCamel)))
            throw new InvalidCamelException(winningCamel);
        else {

            log.info("Inside the SetWinningCamel method");
            racecamels.forEach(camel -> {
                if (!camel.getDidwin() && camel.getNumber().equals(winningCamel)) {
                    log.info(MessageFormat.format("setting current winning camel to TRUE. Camel No. : {1}, Camel Name : {0}", camel.getName(), camel.getNumber()));
                    camel.setDidwin(true);
                }
                if (camel.getDidwin() && !camel.getNumber().equals(winningCamel)) {
                    log.info(MessageFormat.format("setting previous winning camel to FALSE. Camel No. : {1}, Camel Name : {0}", camel.getName(), camel.getNumber()));
                    camel.setDidwin(false);
                }
            });
        }
    }

    public void showStatus(){
        log.info("Displaying the Current Application status");

        System.out.println("Inventory:");
        inventory.forEach((key, value) -> System.out.println(MessageFormat.format("{0}{1},{2}", Constants.DOLLAR_SIGN,  key , value )));

        System.out.println("Camels:");
        racecamels.forEach(camel -> System.out.println(MessageFormat.format("{0},{1},{2},{3}"
                , camel.getNumber(), camel.getName(), camel.getOdds(), camel.getDidwin().equals(true)?"won":"lost")));
    }

}
