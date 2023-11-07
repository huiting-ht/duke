package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

/**
 * Represents a command that finds matching tasks from the task list.
 */
public class FindCommand extends Command {
    /**
     * FindCommand constructor.
     *
     * @param keyword Keyword to find matching tasks.
     */
    public FindCommand(String keyword) {
        if (tasks.isEmpty()) {
            logs.add(Messages.EMPTY_LIST);
        } else {
            saveLogs(keyword);
        }
    }

    /**
     * Saves list of tasks with matching keyword.
     *
     * @param keyword Keyword of the tasks.
     */
    public void saveLogs(String keyword) {
        int itemNum = 1;

        logs.add(Messages.MATCHING_ITEMS);
        for (Task task : tasks) {
            storeMatchingTask(task, itemNum, keyword);
            itemNum++;
        }

        if (logs.size() == 1) {
            logs.add(Messages.EMPTY_LIST);
            logs.add(Messages.NO_MATCHING_ITEMS);
        }
    }

    /**
     * Stores matching task to the logs.
     *
     * @param task    Task to be checked.
     * @param itemNum Item number of the task.
     * @param keyword Keyword to find matching tasks.
     */
    public void storeMatchingTask(Task task, int itemNum, String keyword) {
        String taskType = task.getType();

        String taskDescription = task.getDescription();
        boolean isDescription = taskDescription.contains(keyword);

        boolean isFromDateTime = false;
        boolean isToDateTime = false;
        boolean isDueDateTime = false;

        if (taskType.contains("EVENT")) {
            String taskFromDateTime = ((Event) task).getFromDateTime();
            isFromDateTime = taskFromDateTime.contains(keyword);
            String taskToDateTime = ((Event) task).getToDateTime();
            isToDateTime = taskToDateTime.contains(keyword);
        } else if (taskType.contains("DEADLINE")) {
            String taskDueDateTime = ((Deadline) task).getDueDateTime();
            isDueDateTime = taskDueDateTime.contains(keyword);
        }

        boolean isMatch = isDescription || isFromDateTime || isToDateTime || isDueDateTime;

        if (isMatch) {
            logs.add(itemNum + ". " + task.getTask());
        }
    }
}
