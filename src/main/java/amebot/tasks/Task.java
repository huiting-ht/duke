package amebot.tasks;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected String status;
    protected final String SELECT = "[✓] ";
    protected final String UNSELECT = "[ ] ";
    protected static int listSize;

    /**
     * Creates a task.
     *
     * @param isSelected  Whether the task is selected.
     * @param description The description of the task.
     */
    public Task(boolean isSelected, String description) {
        type = "";
        this.description = description.toUpperCase();

        if (isSelected) {
            status = SELECT;
        } else {
            status = UNSELECT;
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
     * @param desc The description of the task.
     */
    public void setDescription(String desc) {
        this.description = desc;
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
     * Set the status of the task to selected.
     */
    public void setSelectStatus() {
        this.status = SELECT;
    }

    /**
     * Set the status of the task to unselected.
     */
    public void setUnselectStatus() {
        this.status = UNSELECT;
    }

    /**
     * Get the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatus() {
        return status;
    }

    public String getFromDateTime() {
        return "";
    }

    public String getToDateTime() {
        return "";
    }

    public String getDueDateTime() {
        return "";
    }

    /**
     * Get the size of the list.
     *
     * @return The size of the list.
     */
    public static int getListSize() {
        return listSize;
    }
}
