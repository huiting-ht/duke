package amebot.storage;

import amebot.enumerations.Keyword;
import amebot.tasks.Task;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

/**
 * TaskEncoder class encodes the Task object into a string.
 */
public class TaskEncoder {
    private static final String DELIMITER = "|";

    /**
     * Encodes the Task object into a string.
     *
     * @param task The task to be encoded.
     * @return The encoded task.
     */
    public static String encodeTask(Task task) {
        String taskDetail = "";

        String type = task.getType().replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());

        String status = task.getStatus();
        String description = task.getDescription();

        switch (commandType) {
            case TODO:
                taskDetail = type + DELIMITER + status + DELIMITER + description;
                break;
            case EVENT:
                String fromDateTime = ((Event) task).getFromDateTime();
                String toDateTime = ((Event) task).getToDateTime();
                taskDetail = type + DELIMITER + status + DELIMITER + description + DELIMITER + fromDateTime + DELIMITER + toDateTime;
                break;
            case DEADLINE:
                String dueDateTime = ((Deadline) task).getDueDateTime();
                taskDetail = type + DELIMITER + status + DELIMITER + description + DELIMITER + dueDateTime;
                break;
        }

        return taskDetail;
    }
}
