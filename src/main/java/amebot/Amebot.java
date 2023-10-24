package amebot;

import amebot.storage.Storage;
import amebot.ui.UserInterface;
import amebot.parser.Parser;
import amebot.commands.Command;
import amebot.commands.ExitCommand;

import java.util.ArrayList;

/**
 * The main class of the Duke application.
 */
public class Amebot {
    private UserInterface userInterface;

    /**
     * The main method of the Duke application.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        Amebot amebot = new Amebot();
        amebot.launch();
    }

    /**
     * Launches the Duke application.
     */
    public void launch() {
        this.userInterface = new UserInterface();
        this.userInterface.printWelcome();

        Command command = new Command();
        Storage storage = new Storage();
        storage.loadTasks(command);

        render(command);

        storage.saveTasks();
    }

    /**
     * Renders the Duke application.
     *
     * @param command The command to be executed.
     */
    public void render(Command command) {
        String commandLine;

        do {
            commandLine = this.userInterface.getInputCommand();
            ArrayList<String> parsedCommand = new Parser().parseCommand(commandLine);
            ArrayList<String> logs = command.executeCommand(parsedCommand);
            this.userInterface.printOutput(logs);
        } while (!ExitCommand.isExit(commandLine));
    }
}
