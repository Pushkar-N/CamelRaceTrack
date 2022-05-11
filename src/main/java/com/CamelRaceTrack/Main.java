package com.CamelRaceTrack;

import com.CamelRaceTrack.Controller.Controller;
import com.CamelRaceTrack.ExceptionHandling.*;
import com.CamelRaceTrack.Interfaces.Command;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;
import java.util.Scanner;

import static com.CamelRaceTrack.Controller.Controller.*;


public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("******************** Starting Application *************************");
        log.info("Initialising application.");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Controller controller = context.getBean("controller", Controller.class);

        while(true) {
            try {
                controller.showStatus();

                log.info(">>>>>>>>>>>>>>>>> Requesting user input >>>>>>>>>>>>>>>");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                log.info(MessageFormat.format("Provided user input : {0}", userInput));

                Command c = controller.parseInput(userInput.toLowerCase());
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
