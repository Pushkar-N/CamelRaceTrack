package com.CamelRaceTrack;

public class UserCommand {
    public int CamelNumber;
    public float BetAmount;
    public Character Command;
    public String UserInputCommand;

    public int getCamelNumber() {
        return CamelNumber;
    }

    public Character getCommand() {
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

//    public void setUserInputCommand(String userInputCommand) {
//        UserInputCommand = userInputCommand;
//    }

    public void setCamelNumber(int camelNumber) {
        CamelNumber = camelNumber;
    }

    public UserCommand(String userInput){
        this.BetAmount = (float)(Constants.DEFAULT) ;
        this.CamelNumber = 0;
        this.Command = Constants.EMPTY_CHARACTER;
        this.UserInputCommand = userInput;
    }
}
