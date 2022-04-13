package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Common.Constants;

public class UserCommand {
    private int CamelNumber;
    private int BetAmount;
    private char Command;
    private final String UserInputCommand;

    public int getCamelNumber() {
        return CamelNumber;
    }

    public char getCommand() {
        return Command;
    }

    public float getBetAmount() {
        return BetAmount;
    }

    public String getUserInputCommand() {
        return UserInputCommand;
    }

    public void setCommand(Character command) {
        Command = command;
    }

    public void setBetAmount(int betAmount) {
        BetAmount = betAmount;
    }

    public void setCamelNumber(int camelNumber) { CamelNumber = camelNumber; }

    public UserCommand(String userInput){
        this.BetAmount = (Constants.DEFAULT) ;
        this.CamelNumber = 0;
        this.Command = Constants.EMPTY_CHARACTER;
        this.UserInputCommand = userInput;
    }
}
