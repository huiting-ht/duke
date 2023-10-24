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
    protected static ArrayList<String> logs;

    /**
     * Executes the command from the loaded file
     *
     * @param parsedTask parsed task from the loaded file
     * @return logs for output
     */
    public void executeLoadTaskCommand(ArrayList<String> parsedTask) {
        logs = new ArrayList<>();

        Keyword commandType = Keyword.valueOf(parsedTask.get(0).trim().toUpperCase());
        boolean isSelected = Boolean.parseBoolean(parsedTask.get(1));
        String description = parsedTask.get(2);

        switch (commandType) {
            case TODO:
                new AddCommand(isSelected, description);
                break;
            case EVENT:
                String fromDateTime = parsedTask.get(3);
                String toDateTime = parsedTask.get(4);
                new AddCommand(isSelected, description, fromDateTime, toDateTime);
                break;
            case DEADLINE:
                String dueTime = parsedTask.get(3);
                new AddCommand(isSelected, description, dueTime);
                break;
        }
    }

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
            boolean isSelected = false;
            String description;
            int index;

            switch (commandType) {
                case TODO:
                    description = parsedCommand.get(1);
                    new AddCommand(isSelected, description);
                    break;
                case EVENT:
                    description = parsedCommand.get(1);
                    if (parsedCommand.size() == 4) {
                        String fromDateTime = parsedCommand.get(2);
                        String toDateTime = parsedCommand.get(3);
                        new AddCommand(isSelected, description, fromDateTime, toDateTime);
                    }
                    break;
                case DEADLINE:
                    description = parsedCommand.get(1);
                    if (parsedCommand.size() == 3) {
                        String dueTime = parsedCommand.get(2);
                        new AddCommand(isSelected, description, dueTime);
                    }
                    break;
                case SELECT:
                    // Fallthrough
                case UNSELECT:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new UpdateCommand(index, commandType.toString());
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
