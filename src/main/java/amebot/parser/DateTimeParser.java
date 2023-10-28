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
    private static final int START_INDEX = 0;
    private static final int END_INDEX_OF_FROM = 6;
    private static final int START_INDEX_OF_FROM_DATETIME = 7;
    private static final int END_INDEX_OF_DUE = 5;
    private static final int START_INDEX_OF_DUE_DATETIME = 6;

    /**
     * Parses the date and time of the task.
     *
     * @param command    The user's input.
     * @param startIndex The starting index of the date and time in the command.
     * @param endIndex   The ending index of the date and time in the command.
     * @return An ArrayList of the parsed date and time.
     */
    public ArrayList<String> parseDateTime(String command, int startIndex, int endIndex) {
        String dateTime = command.substring(startIndex, endIndex);

        if (!isValidDateTimeFormat(dateTime)) {
            System.out.println(Messages.INVALID_DATE);
        } else {
            splitDateTime(dateTime);
        }

        return parsedCommand;
    }

    /**
     * Checks if the date and time format of the task is valid.
     *
     * @param dateTime The date and time of the task.
     * @return A boolean value to indicate if the date and time format of the task is valid.
     */
    public boolean isValidDateTimeFormat(String dateTime) {
        boolean isEventDateTime = dateTime.matches(Regex.FROM_PATTERN + Regex.DATE_TIME_PATTERN + Regex.TO_PATTERN + Regex.DATE_TIME_PATTERN);
        boolean isDeadlineDateTime = dateTime.matches(Regex.DUE_PATTERN + Regex.DATE_TIME_PATTERN);

        return isEventDateTime || isDeadlineDateTime;
    }

    /**
     * Splits the date and time of the task into the start date and time and
     * end date and time.
     *
     * @param dateTime The date and time of the task.
     */
    public void splitDateTime(String dateTime) {
        final int END_INDEX = dateTime.length();

        if (dateTime.contains(Regex.FROM_PATTERN)) {
            final int END_INDEX_OF_FROM_DATETIME = dateTime.indexOf(Regex.TO_PATTERN);

            String from = dateTime.substring(START_INDEX, END_INDEX_OF_FROM).replace('/', '(');
            String fromDate = dateTime.substring(START_INDEX_OF_FROM_DATETIME, END_INDEX_OF_FROM_DATETIME);
            String parsedFromDateTime = parseDateTime(fromDate);
            String fromDateTime = from + ": " + parsedFromDateTime;

            final int START_INDEX_OF_TO = END_INDEX_OF_FROM_DATETIME + 1;
            final int END_INDEX_OF_TO = END_INDEX_OF_FROM_DATETIME + 4;
            String to = dateTime.substring(START_INDEX_OF_TO, END_INDEX_OF_TO).replace('/', ' ');
            final int START_INDEX_OF_TO_DATETIME = END_INDEX_OF_FROM_DATETIME + 5;
            String toDate = dateTime.substring(START_INDEX_OF_TO_DATETIME, END_INDEX);
            String parsedToDateTime = parseDateTime(toDate);
            String toDateTime = to + ": " + parsedToDateTime + ")";

            parsedCommand.add(fromDateTime);
            parsedCommand.add(toDateTime);
        } else {
            String due = dateTime.substring(START_INDEX, END_INDEX_OF_DUE).replace('/', '(');
            String dueDate = dateTime.substring(START_INDEX_OF_DUE_DATETIME, END_INDEX);
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
        }

        return getDate(dateTime, dateTimeFormatter);
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
