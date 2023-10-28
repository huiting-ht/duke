package amebot.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    protected static final String TODO = "[TODO] ";

    /**
     * Creates a ToDo task.
     *
     * @param isMarked    Whether the task is marked.
     * @param description The description of the task.
     */
    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
        super.type = TODO;
    }

    /**
     * Get the information of the task.
     *
     * @return The information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description;
    }
}
