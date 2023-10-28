package amebot.commands;

import amebot.common.Messages;

/**
 * UnmarkCommand class
 *
 * <p>Command to unmark the status of a task
 */
public class UnmarkCommand extends Command {
    /**
     * UnmarkCommand constructor
     *
     * @param index index of the task to be set as unmark
     */
    public UnmarkCommand(int index) {
        boolean isValidIndex = index > 0 && index <= tasks.size();

        if (isValidIndex) {
            tasks.get(index - 1).setStatusAsUnmark();
            logs.add(Messages.STATUS_UNMARKED);

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
