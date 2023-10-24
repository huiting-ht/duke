package amebot.parser;

import amebot.common.Messages;
import amebot.common.Regex;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.EnumSet;
import java.util.ArrayList;

/**
 * Represents a parser that parses the user's input.
 */
public class Parser {
    protected ArrayList<String> parsedCommand = new ArrayList<>();

    /**
     * Parses the user's input and returns the parsed command.
     *
     * @param command The user's input.
     * @return The parsed command.
     */
    public ArrayList<String> parseCommand(String command) {
        String[] words = command.trim().split(" ");
        String commandName = words[0].toUpperCase();

        if (isValidCommand(commandName)) {
            Keyword commandType = Keyword.valueOf(commandName);

            switch (commandType) {
                case TODO:
                    parseTodo(commandName, command);
                    break;
                case EVENT:
                    parseEvent(commandName, command);
                    break;
                case DEADLINE:
                    parseDeadline(commandName, command);
                    break;
                case SELECT:
                    // Fallthrough
                case UNSELECT:
                    // Fallthrough
                case REMOVE:
                    parseUpdate(commandName, command, words);
                    break;
                default:
                    parseDefault(commandName, command);
                    break;
            }
        } else {
            System.out.println(Messages.INVALID_COMMAND);
        }

        return parsedCommand;
    }

    /**
     * Checks if the command type is valid.
     *
     * @param commandName The command name.
     * @return True if the command type is valid, false otherwise.
     */
    public boolean isValidCommand(String commandName) {
        EnumSet<Keyword> commandList = EnumSet.allOf(Keyword.class);

        for (Keyword commandType : commandList) {
            boolean isMatch = commandType.toString().equalsIgnoreCase(commandName);

            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    /**
     * Parses the description of the ToDo task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     */
    public void parseTodo(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int size = command.length();
            setDescription(command, 5, size);
        } else {
            System.out.println(Messages.INVALID_DESC);
        }
    }

    /**
     * Parses the description and dateTime of the Event task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     */
    public void parseEvent(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(Regex.FROM_PATTERN);
            setDescription(command, 6, index);
            ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, index);
            parsedCommand.addAll(parsedDateTime);
        } else {
            System.out.println(Messages.INVALID_DESC_DATE);
        }
    }

    /**
     * Parses the description and dateTime of the Deadline task.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     */
    public void parseDeadline(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            int index = command.indexOf(Regex.DUE_PATTERN);
            setDescription(command, 9, index);
            ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, index);
            parsedCommand.addAll(parsedDateTime);
        } else {
            System.out.println(Messages.INVALID_DESC_DATE);
        }
    }

    /**
     * Sets the description of the task.
     *
     * @param command    The user's input.
     * @param startIndex The start index of the description.
     * @param endIndex   The end index of the description.
     */
    public void setDescription(String command, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            String description = command.substring(startIndex, endIndex);
            parsedCommand.add(description);
        }
    }

    /**
     * Parses the index of the task to be updated.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     * @param words       The user's input split into an array of words.
     */
    public void parseUpdate(String commandName, String command, String[] words) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
            setIndex(words[1]);
        } else {
            System.out.println(Messages.INVALID_INDEX);
        }
    }

    /**
     * Sets the index of the task to be updated.
     *
     * @param index The index of the task to be updated.
     */
    public void setIndex(String index) {
        int taskIndex = Integer.parseInt(index);

        if (isValidIndex(taskIndex)) {
            parsedCommand.add(index);
        } else {
            parsedCommand.add("-1");
            System.out.println(Messages.INVALID_INDEX_VALUE
                    + Task.getListSize()
                    + "!");
        }
    }

    /**
     * Checks if the index of the task to be updated is valid.
     *
     * @param taskIndex The index of the task to be updated.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int taskIndex) {
        return taskIndex <= Task.getListSize();
    }

    /**
     * Parses and sets the command type.
     *
     * @param commandName The command name.
     * @param command     The user's input.
     */
    public void parseDefault(String commandName, String command) {
        if (isValidCommandFormat(command)) {
            parsedCommand.add(commandName);
        }
    }

    /**
     * Checks if the command format is valid.
     *
     * @param command The user's input.
     * @return True if the command format is valid, false otherwise.
     */
    public boolean isValidCommandFormat(String command) {
        return command.matches(Regex.TODO_COMMAND) || command.matches(Regex.EVENT_COMMAND) || command.matches(Regex.DEADLINE_COMMAND) ||
                command.matches(Regex.SELECT_INDEX_COMMAND) || command.matches(Regex.LIST_COMMAND) ||
                command.matches(Regex.REMOVE_INDEX_COMMAND) || command.matches(Regex.BYE_COMMAND);
    }
}
