package amebot.commands;

import amebot.common.Messages;

/**
 * MarkCommand class
 *
 * <p>Command to mark the status of a task
 */
public class MarkCommand extends Command {
    /**
     * MarkCommand constructor
     *
     * @param index index of the task to be set as marked
     */
    public MarkCommand(int index) {
        boolean isValidIndex = index > 0 && index <= tasks.size();
        if (isValidIndex) {
            tasks.get(index - 1).setStatusAsMark();
            logs.add(Messages.STATUS_MARKED);

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
