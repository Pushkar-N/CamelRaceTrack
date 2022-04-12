package com.CamelRaceTrack;

import com.CamelRaceTrack.Controller.*;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Models.UserCommand;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Controller.InitializeApplication();

        while(true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            try {

                UserCommand userCommand = Controller.ParseInputData(userInput);
                if(Controller.ValidateRequest(userCommand))
                   Controller.ProcessRequest(userCommand);

            } catch (Exception | InvalidBetException | InvalidCamelException | InvalidCommandException | NoPayoutException | InsufficientFundException ex) {
                if(ex instanceof InvalidCamelException || ex instanceof  InvalidBetException || ex instanceof InvalidCommandException
                        || ex instanceof NoPayoutException || ex instanceof InsufficientFundException)
                    continue;
                else
                    System.out.println("Exception in application :" + ex.getMessage());
            }
        }

    }


}
