package amebot.commands;

import amebot.common.Messages;

/**
 * UpdateCommand class
 *
 * <p>Command to update the status of a task
 */
public class UpdateCommand extends Command {
    /**
     * UpdateCommand constructor
     *
     * @param index       index of the task to be updated
     * @param commandType type of task status to be updated
     */
    public UpdateCommand(int index, String commandType) {
        if (index > 0) {
            if (commandType.equalsIgnoreCase("select")) {
                tasks.get(index - 1).setSelectStatus();
                logs.add(Messages.STATUS_SELECTED);
            } else {
                tasks.get(index - 1).setUnselectStatus();
                logs.add(Messages.STATUS_UNSELECTED);
            }

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
