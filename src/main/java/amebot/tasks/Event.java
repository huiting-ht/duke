package amebot.tasks;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected final String EVENT = "[EVENT] ";
    protected String fromDateTime;
    protected String toDateTime;

    /**
     * Creates an Event task.
     *
     * @param isSelected   Whether the task is selected.
     * @param description  The description of the task.
     * @param fromDateTime The starting date and time of the event.
     * @param toDateTime   The ending date and time of the event.
     */
    public Event(boolean isSelected, String description, String fromDateTime, String toDateTime) {
        super(isSelected, description);
        super.type = EVENT;
        this.fromDateTime = fromDateTime.toUpperCase();
        this.toDateTime = toDateTime.toUpperCase();
    }

    /**
     * Get the information of the task.
     *
     * @return The information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description + fromDateTime + toDateTime;
    }

    /**
     * Get the starting date and time of the event.
     *
     * @return The starting date and time of the event.
     */
    @Override
    public String getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Get the ending date and time of the event.
     *
     * @return The ending date and time of the event.
     */
    @Override
    public String getToDateTime() {
        return toDateTime;
    }
}
