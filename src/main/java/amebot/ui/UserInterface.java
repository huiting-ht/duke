package amebot.ui;

import amebot.common.Title;
import amebot.common.Messages;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * UserInterface class is responsible for getting the user input command(s) and
 * printing the welcome message and output.
 */
public class UserInterface {
    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        Title.printHeader();
        Title.printGreeting();
    }

    /**
     * Get the user input command(s).
     */
    public String getInputCommand() {
        Scanner scanInput = new Scanner(System.in);
        String commandLine = scanInput.nextLine().toLowerCase();
        return commandLine;
    }

    /**
     * Prints the output.
     *
     * @param logs ArrayList of logs to be printed.
     */
    public void printOutput(ArrayList<String> logs) {
        if (!logs.isEmpty()) {
            System.out.println(Messages.TOP_BORDER);

            for (String log : logs) {
                System.out.println(log);
            }

            System.out.println(Messages.BOTTOM_BORDER);
        }
    }
}
