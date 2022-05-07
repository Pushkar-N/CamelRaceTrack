package com.CamelRaceTrack;

import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Interfaces.Command;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Scanner;

import static com.CamelRaceTrack.Controller.Controller.*;


public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("******************** Starting Application *************************");
       Initialize();

        while(true) {
            try {
                DisplayCurrentApplicationStatus();

                log.info(">>>>>>>>>>>>>>>>> Requesting user input >>>>>>>>>>>>>>>");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                log.info(MessageFormat.format("Provided user input : {0}", userInput));

                Command c = ParseInput(userInput.toLowerCase());
                c.processRequest();

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
