package amebot.tasks;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected static final String EVENT = "[EVENT] ";
    protected String fromDateTime;
    protected String toDateTime;

    /**
     * Creates an Event task.
     *
     * @param isMarked     Whether the task is marked.
     * @param description  The description of the task.
     * @param fromDateTime The starting date and time of the event.
     * @param toDateTime   The ending date and time of the event.
     */
    public Event(boolean isMarked, String description, String fromDateTime, String toDateTime) {
        super(isMarked, description);
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
     * Set the starting date and time of the event.
     *
     * @param fromDateTime The starting date and time of the event.
     */
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /**
     * Get the starting date and time of the event.
     *
     * @return The starting date and time of the event.
     */
    public String getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Set the ending date and time of the event.
     *
     * @param toDateTime The ending date and time of the event.
     */
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    /**
     * Get the ending date and time of the event.
     *
     * @return The ending date and time of the event.
     */
    public String getToDateTime() {
        return toDateTime;
    }
}
