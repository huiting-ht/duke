package amebot.commands;

/**
 * ExitCommand class
 *
 * <p>Command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * Checks if the command is an exit command
     *
     * @param command command from the user input
     * @return true if the command is an exit command, false otherwise
     */
    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }
}
