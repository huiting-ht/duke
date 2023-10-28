package amebot.storage;

import amebot.common.Messages;
import amebot.commands.Command;
import amebot.tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Storage class handles the loading of tasks from the file and
 * saving of tasks to the file.
 */
public class Storage {
    protected static final String FILE_PATHNAME = "./src/main/java/data/amebot.txt";
    private File tasksFile = new File(FILE_PATHNAME);

    /**
     * Loads tasks from the file.
     */
    public void loadTasks() {
        if (!isTasksFileFound() || isTasksFileEmpty()) {
            return;
        }

        try {
            FileReader tasksFileReader = new FileReader(tasksFile);
            BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
            String task = "";

            while ((task = bufferedReader.readLine()) != null) {
                TaskDecoder.decodeTask(task);
            }

            System.out.println(Messages.SUCCESS_LOAD_TASK);

            bufferedReader.close();
            tasksFileReader.close();
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }

    /**
     * Checks if the file exists.
     * If the file does not exist, a new file will be created.
     *
     * @return True if the file exists, false otherwise.
     */
    public boolean isTasksFileFound() {
        if (tasksFile.exists()) {
            return true;
        }

        System.out.println(Messages.FILE_NOT_FOUND);
        createNewTasksFile();
        return false;
    }

    /**
     * Creates a new file if the file does not exist.
     */
    public void createNewTasksFile() {
        try {
            boolean isCreated = tasksFile.createNewFile();
            if (isCreated) {
                System.out.println(Messages.FILE_CREATED);
            }
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }

    /**
     * Checks if the file is empty.
     *
     * @return True if the file is empty, false otherwise.
     */
    public boolean isTasksFileEmpty() {
        if (tasksFile.length() == 0) {
            System.out.println((Messages.EMPTY_FILE));
            return true;
        }

        return false;
    }

    /**
     * Saves tasks to the file.
     */
    public void saveTasks() {
        ArrayList<Task> tasksList = Command.getTasks();
        String taskDetail = "";

        try {
            FileWriter tasksFileWriter = new FileWriter(tasksFile);
            BufferedWriter bufferedWriter = new BufferedWriter(tasksFileWriter);

            for (Task task : tasksList) {
                taskDetail = TaskEncoder.encodeTask(task);

                bufferedWriter.write(taskDetail);
                bufferedWriter.newLine();
            }

            System.out.println(Messages.SUCCESS_SAVE_TASK);

            bufferedWriter.close();
            tasksFileWriter.close();
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }
}
