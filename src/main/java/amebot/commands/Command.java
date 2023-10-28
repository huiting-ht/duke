package amebot.commands;

import amebot.common.Messages;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.ArrayList;

/**
 * Command class
 *
 * <p>Parent class for all commands
 */

public class Command {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static ArrayList<String> logs = new ArrayList<>();

    /**
     * Executes the command from the user input
     *
     * @param parsedCommand parsed command from the user input
     * @return logs for output
     */
    public ArrayList<String> executeCommand(ArrayList<String> parsedCommand) {
        logs = new ArrayList<>();

        if (!parsedCommand.isEmpty()) {
            Keyword commandType = Keyword.valueOf(parsedCommand.get(0));
            boolean isMarked = false;
            String description;
            int index;

            switch (commandType) {
                case TODO:
                    description = parsedCommand.get(1);
                    new AddCommand(isMarked, description);
                    break;
                case EVENT:
                    description = parsedCommand.get(1);
                    String fromDateTime = parsedCommand.get(2);
                    String toDateTime = parsedCommand.get(3);
                    new AddCommand(isMarked, description, fromDateTime, toDateTime);
                    break;
                case DEADLINE:
                    description = parsedCommand.get(1);
                    String dueTime = parsedCommand.get(2);
                    new AddCommand(isMarked, description, dueTime);
                    break;
                case MARK:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new MarkCommand(index);
                    break;
                case UNMARK:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new UnmarkCommand(index);
                    break;
                case REMOVE:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new DeleteCommand(index);
                    break;
                case LIST:
                    new ListCommand();
                    break;
                case BYE:
                    new ExitCommand();
                    logs.add(Messages.EXIT_MESSAGE);
                    break;
            }
        }

        return logs;
    }

    /**
     * Gets the list of tasks
     *
     * @return list of tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
