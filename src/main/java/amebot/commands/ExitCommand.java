package amebot.commands;

/**
 * ExitCommand class
 *
 * <p>Command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * ExitCommand constructor
     */
    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }
}
