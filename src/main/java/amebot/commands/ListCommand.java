package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;

/**
 * ListCommand class
 *
 * <p>Command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * ListCommand constructor
     */
    public ListCommand() {
        if (tasks.isEmpty()) {
            logs.add(Messages.EMPTY_LIST);
        } else {
            saveLogs();
        }
    }

    /**
     * Saves the logs for output
     */
    public void saveLogs() {
        int itemNum = 1;

        logs.add(Messages.FULL_LIST);
        for (Task task : tasks) {
            logs.add(itemNum + ". " + task.getTask());
            itemNum++;
        }
    }
}
