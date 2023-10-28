package amebot.storage;

import amebot.enumerations.Keyword;
import amebot.commands.AddCommand;

/**
 * TaskDecoder class is used to decode task from the storage file and
 * add as a task object.
 */
public class TaskDecoder {
    private static final String MARKED = "✓";

    /**
     * This method decodes task from the storage file and
     * add as a task object.
     *
     * @param task The task to be decoded.
     */
    public static void decodeTask(String task) {
        String[] taskDetail = task.toLowerCase().split("\\|");

        String type = taskDetail[0].replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());

        boolean isMarked = taskDetail[1].contains(MARKED);
        String description = taskDetail[2];

        switch (commandType) {
            case TODO:
                new AddCommand(isMarked, description);
                break;
            case EVENT:
                String fromDateTime = taskDetail[3];
                String toDateTime = taskDetail[4];
                new AddCommand(isMarked, description, fromDateTime, toDateTime);
                break;
            case DEADLINE:
                String dueDateTime = taskDetail[3];
                new AddCommand(isMarked, description, dueDateTime);
                break;
        }
    }
}
