package amebot.parser;

import amebot.common.Messages;
import amebot.common.Regex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * DateTimeParser class is used to parse the date and time of the task.
 */
public class DateTimeParser extends Parser {
    /**
     * Parses the date and time of the task.
     *
     * @param command The user's input.
     * @param index   The index of the date and time in the command.
     * @return An ArrayList of the parsed date and time.
     */
    public ArrayList<String> parseDateTime(String command, int index) {
        int size = command.length();
        String dateTime = command.substring(index, size);

        if (isValidDateTime(dateTime)) {
            splitDateTime(dateTime);
        } else {
            System.out.println(Messages.INVALID_DATE);
        }

        return parsedCommand;
    }

    /**
     * Checks if the date and time format of the task is valid.
     *
     * @param dateTime The date and time of the task.
     * @return A boolean value to indicate if the date and time format of the task is valid.
     */
    public boolean isValidDateTime(String dateTime) {
        boolean isEventDateTime = dateTime.matches(Regex.FROM_PATTERN + Regex.DATE_TIME_PATTERN + Regex.TO_PATTERN + Regex.DATE_TIME_PATTERN);
        boolean isDeadlineDateTime = dateTime.matches(Regex.DUE_PATTERN + Regex.DATE_TIME_PATTERN);

        return isEventDateTime || isDeadlineDateTime;
    }

    /**
     * Splits the date and time of the task into the start date and time and end date and time.
     *
     * @param dateTime The date and time of the task.
     */
    public void splitDateTime(String dateTime) {
        int size = dateTime.length();

        if (dateTime.contains(Regex.FROM_PATTERN)) {
            int index = dateTime.indexOf(Regex.TO_PATTERN);

            String from = dateTime.substring(0, 6).replace('/', '(');
            String fromDate = dateTime.substring(7, index);
            String parsedFromDateTime = parseDateTime(fromDate);
            String fromDateTime = from + ": " + parsedFromDateTime;

            String to = dateTime.substring(index + 1, index + 4).replace('/', ' ');
            String toDate = dateTime.substring(index + 5, size);
            String parsedToDateTime = parseDateTime(toDate);
            String toDateTime = to + ": " + parsedToDateTime + ")";

            parsedCommand.add(fromDateTime);
            parsedCommand.add(toDateTime);
        } else {
            String due = dateTime.substring(0, 5).replace('/', '(');
            String dueDate = dateTime.substring(6);
            String parsedDueDateTime = parseDateTime(dueDate);
            String dueDateTime = due + ": " + parsedDueDateTime + ")";

            parsedCommand.add(dueDateTime);
        }
    }

    /**
     * Parses the date and time of the task.
     *
     * @param dateTime The date and time of the task.
     * @return The parsed date and time of the task.
     */
    public String parseDateTime(String dateTime) {
        String dateTimeFormat = "";

        if (dateTime.contains("-")) {
            dateTimeFormat = "yyyy-MM-dd[ HHmm]";
        } else {
            dateTimeFormat = "dd/MM/yyyy[ HHmm]";
        }

        int size = dateTime.split(" ").length;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

        if (size == 2) {
            return getDateTime(dateTime, dateTimeFormatter);
        } else {
            return getDate(dateTime, dateTimeFormatter);
        }
    }

    /**
     * Gets the date and time of the task.
     *
     * @param dateTime          The date and time of the task.
     * @param dateTimeFormatter The date and time formatter.
     * @return The date and time of the task.
     */
    public String getDateTime(String dateTime, DateTimeFormatter dateTimeFormatter) {
        String parsedDateTime = "";
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy (EEE) h:mma");
            parsedDateTime = localDateTime.format(outputFormatter);
        } catch (Exception err) {
            System.out.println(Messages.INVALID_DATE_TIME_RANGE);
        }

        return parsedDateTime;
    }

    /**
     * Gets the date of the task.
     *
     * @param dateTime          The date and time of the task.
     * @param dateTimeFormatter The date and time formatter.
     * @return The date of the task.
     */
    public String getDate(String dateTime, DateTimeFormatter dateTimeFormatter) {
        String parsedDateTime = "";

        try {
            LocalDate localDate = LocalDate.parse(dateTime, dateTimeFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy (EEE)");
            parsedDateTime = localDate.format(outputFormatter);
        } catch (Exception err) {
            System.out.println(Messages.INVALID_DATE_TIME_RANGE);
        }

        return parsedDateTime;
    }
}
