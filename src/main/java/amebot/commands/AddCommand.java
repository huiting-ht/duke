package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;
import amebot.tasks.ToDo;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

/**
 * AddCommand class
 *
 * <p>Command to add a task
 */
public class AddCommand extends Command {
    protected Task item;

    /**
     * AddCommand constructor
     *
     * @param isSelected  status of the task
     * @param description description of the task
     */
    public AddCommand(boolean isSelected, String description) {
        item = new ToDo(isSelected, description);
        tasks.add(item);
        saveLogs();
    }

    /**
     * AddCommand constructor for event
     *
     * @param isSelected   status of the task
     * @param description  description of the task
     * @param fromDateTime start date and time of the task
     * @param toDateTime   end date and time of the task
     */
    public AddCommand(boolean isSelected, String description, String fromDateTime, String toDateTime) {
        item = new Event(isSelected, description, fromDateTime, toDateTime);
        tasks.add(item);
        saveLogs();
    }

    /**
     * AddCommand constructor for deadline
     *
     * @param isSelected  status of the task
     * @param description description of the task
     * @param dueDateTime due date and time of the task
     */
    public AddCommand(boolean isSelected, String description, String dueDateTime) {
        item = new Deadline(isSelected, description, dueDateTime);
        tasks.add(item);
        saveLogs();
    }

    /**
     * Saves the logs for output
     */
    public void saveLogs() {
        logs.add(item.getTask());
        logs.add(Messages.SUCCESS_ADD_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);
    }
}
