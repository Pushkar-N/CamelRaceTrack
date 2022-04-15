package com.CamelRaceTrack;

import com.CamelRaceTrack.Controller.*;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Models.UserCommand;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Scanner;


public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("******************** Starting Application *************************");
       Controller.InitializeApplication();

        while(true) {
            try {
                Controller.DisplayCurrentApplicationStatus();

                log.info(">>>>>>>>>>>>>>>>> Requesting user input >>>>>>>>>>>>>>>");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                log.info(MessageFormat.format("Provided user input : {0}", userInput));

                UserCommand userCommand = Controller.ParseInputData(userInput);
                if(Controller.ValidateRequest(userCommand))
                   Controller.ProcessRequest(userCommand);

            } catch (Exception | InvalidBetException | InvalidCamelException | InvalidCommandException | NoPayoutException | InsufficientFundException ex) {
                if(ex instanceof InvalidCamelException || ex instanceof  InvalidBetException || ex instanceof InvalidCommandException
                        || ex instanceof NoPayoutException || ex instanceof InsufficientFundException)
                    continue;
                else {
                    log.error(MessageFormat.format("Exception in application. Please check the logged datapoints. \n" +
                            "Exception details : {0}" +
                            "Exception Stacktrace : {1}" , ex.getMessage(), ex.getStackTrace()));
                    System.out.println(MessageFormat.format("Exception in application : {0}", ex.getMessage()));
                }
            }
        }

    }


}
