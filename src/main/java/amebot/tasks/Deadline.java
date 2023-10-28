package amebot.tasks;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected static final String DEADLINE = "[DEADLINE] ";
    protected String dueDateTime;

    /**
     * Creates a Deadline task.
     *
     * @param isMarked    Whether the task is marked.
     * @param description The description of the task.
     * @param dueDateTime The due date and time of the task.
     */
    public Deadline(boolean isMarked, String description, String dueDateTime) {
        super(isMarked, description);
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
     * Set the due date and time of the task.
     *
     * @param dueDateTime The due date and time of the task.
     */
    public void setDueDateTime(String dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    /**
     * Get the due date and time of the task.
     *
     * @return The due date and time of the task.
     */
    public String getDueDateTime() {
        return dueDateTime;
    }
}
