package amebot.parser;

import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.ArrayList;

/**
 * StorageParser class is used to parse the task from the storage file.
 * It is also used to parse the task to be saved into the storage file.
 */
public class StorageParser extends Parser {
    /**
     * This method parses the task from the storage file.
     *
     * @param task The task to be parsed.
     * @return An ArrayList of String containing the task details.
     */
    public ArrayList<String> parseLoadTask(String task) {
        ArrayList<String> item = new ArrayList<>();
        String[] words = task.toLowerCase().split("\\|");

        String type = words[0].replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());
        item.add(type);

        boolean isSeleted = words[1].contains("✓");
        item.add(String.valueOf(isSeleted));

        String description = words[2];
        item.add(description);

        switch (commandType) {
            case EVENT:
                String fromDateTime = words[3];
                String toDateTime = words[4];
                item.add(fromDateTime);
                item.add(toDateTime);
                break;
            case DEADLINE:
                String dueDateTIme = words[3];
                item.add(dueDateTIme);
                break;
        }

        return item;
    }

    /**
     * This method parses the task to be saved into the storage file.
     *
     * @param task The task to be parsed.
     * @return A String containing the task details.
     */
    public String parseSaveTask(Task task) {
        String item = "";

        String type = task.getType().replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());

        String status = task.getStatus();
        String description = task.getDescription();

        switch (commandType) {
            case TODO:
                item = type + "|" + status + "|" + description;
                break;
            case EVENT:
                String fromDateTime = task.getFromDateTime();
                String toDateTime = task.getToDateTime();
                item = type + "|" + status + "|" + description + "|" + fromDateTime + "|" + toDateTime;
                break;
            case DEADLINE:
                String dueDateTIme = task.getDueDateTime();
                item = type + "|" + status + "|" + description + "|" + dueDateTIme;
                break;
        }

        return item;
    }
}
