package amebot.tasks;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected String status;
    protected static final String MARK = "[✓] ";
    protected static final String UNMARK = "[ ] ";
    protected static int listSize;

    /**
     * Creates a task.
     *
     * @param isMarked    Whether the task is marked.
     * @param description The description of the task.
     */
    public Task(boolean isMarked, String description) {
        this.description = description.toUpperCase();

        if (isMarked) {
            status = MARK;
        } else {
            status = UNMARK;
        }

        listSize++;
    }

    public abstract String getTask();

    /**
     * Set the type of the task.
     *
     * @param type The type of task.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the type of the task.
     *
     * @return The type of task.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the description of the task.
     *
     * @param description The description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the status of the task as mark.
     */
    public void setStatusAsMark() {
        this.status = MARK;
    }

    /**
     * Set the status of the task as unmark.
     */
    public void setStatusAsUnmark() {
        this.status = UNMARK;
    }

    /**
     * Get the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the size of the task list.
     *
     * @return The size of the task list.
     */
    public static int getListSize() {
        return listSize;
    }
}
