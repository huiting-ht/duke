package amebot.commands;

import amebot.common.Messages;

/**
 * DeleteCommand class
 *
 * <p>Command to delete a task
 */
public class DeleteCommand extends Command {
    /**
     * DeleteCommand constructor
     *
     * @param index index of the task to be deleted
     */
    public DeleteCommand(int index) {
        boolean isValidIndex = index > 0 && index <= tasks.size();

        if (isValidIndex) {
            logs.add(tasks.get(index - 1).getTask());
            tasks.remove(index - 1);
            saveOutput();
        }
    }

    /**
     * Saves the logs for output
     */
    public void saveOutput() {
        logs.add(Messages.SUCCESS_REMOVE_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);
    }
}
