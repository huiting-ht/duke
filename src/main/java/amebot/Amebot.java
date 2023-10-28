package amebot;

import amebot.storage.Storage;
import amebot.ui.UserInterface;
import amebot.parser.Parser;
import amebot.commands.Command;
import amebot.commands.ExitCommand;

import java.util.ArrayList;

/**
 * The main class of the application.
 */
public class Amebot {
    private UserInterface userInterface;

    /**
     * The main method of the application.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        Amebot amebot = new Amebot();
        amebot.launch();
    }

    /**
     * Launches the application.
     */
    public void launch() {
        this.userInterface = new UserInterface();
        this.userInterface.printWelcome();

        Storage storage = new Storage();
        storage.loadTasks();

        render();

        storage.saveTasks();
    }

    /**
     * Renders the application.
     */
    public void render() {
        String commandLine = "";
        Command command = new Command();

        do {
            commandLine = this.userInterface.getInputCommand();
            ArrayList<String> parsedCommand = new Parser().parseCommand(commandLine);
            ArrayList<String> logs = command.executeCommand(parsedCommand);
            this.userInterface.printOutput(logs);
        } while (!ExitCommand.isExit(commandLine));
    }
}
