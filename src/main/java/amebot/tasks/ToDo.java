package amebot.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    protected final String TODO = "[TODO] ";

    /**
     * Creates a ToDo task.
     *
     * @param isSelected  Whether the task is selected.
     * @param description The description of the task.
     */
    public ToDo(boolean isSelected, String description) {
        super(isSelected, description);
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
