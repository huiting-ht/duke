package amebot.tasks;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected final String DEADLINE = "[DEADLINE] ";
    protected String dueDateTime;

    /**
     * Creates a Deadline task.
     *
     * @param isSelected  Whether the task is selected.
     * @param description The description of the task.
     * @param dueDateTime The due date and time of the task.
     */
    public Deadline(boolean isSelected, String description, String dueDateTime) {
        super(isSelected, description);
        super.type = DEADLINE;
        this.dueDateTime = dueDateTime.toUpperCase();
    }

    /**
     * Get the information of the task.
     *
     * @return The information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description + dueDateTime;
    }

    /**
     * Get the due date and time of the task.
     *
     * @return The due date and time of the task.
     */
    @Override
    public String getDueDateTime() {
        return dueDateTime;
    }
}
