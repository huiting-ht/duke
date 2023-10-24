package amebot.storage;

import amebot.commands.Command;
import amebot.common.Messages;
import amebot.parser.StorageParser;
import amebot.tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class handles the loading of tasks from the file and
 * saving of tasks to the file.
 */
public class Storage {
    private static final String filePathname = "./src/main/java/data/amebot.txt";
    private File tasksFile = new File(filePathname);

    /**
     * Loads tasks from the file if the file exists.
     * If the file does not exist, a new file will be created.
     *
     * @param command Command object that executes the load task command.
     */
    public void loadTasks(Command command) {
        if (findTasksFile()) {
            ArrayList<String> tasksList = getTasksFileData();
            storeTasks(command, tasksList);
        } else {
            createNewTasksFile();
        }
    }

    /**
     * Creates a new file if the file does not exist.
     */
    public void createNewTasksFile() {
        try {
            if (tasksFile.createNewFile()) {
                System.out.println(Messages.FILE_CREATED);
            }
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }

    /**
     * Checks if the file exists.
     *
     * @return True if the file exists, false otherwise.
     */
    public boolean findTasksFile() {
        boolean isFileFound = false;

        if (tasksFile.exists()) {
            isFileFound = true;
        } else {
            System.out.println(Messages.FILE_NOT_FOUND);
        }

        return isFileFound;
    }

    /**
     * Reads the data from the file and stores it in an ArrayList.
     *
     * @return ArrayList of tasks in the file.
     */
    public ArrayList<String> getTasksFileData() {
        ArrayList<String> tasksList = new ArrayList<>();

        if (!isFileEmpty()) {
            try {
                FileReader tasksFileReader = new FileReader(tasksFile);
                BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
                String task = "";

                while ((task = bufferedReader.readLine()) != null) {
                    tasksList.add(task);
                }

                bufferedReader.close();
                tasksFileReader.close();
            } catch (IOException err) {
                System.out.println((Messages.ERROR_MESSAGE));
            }
        }

        return tasksList;
    }

    /**
     * Checks if the file is empty.
     *
     * @return True if the file is empty, false otherwise.
     */
    public boolean isFileEmpty() {
        boolean isFileEmpty = false;

        if (tasksFile.length() == 0) {
            isFileEmpty = true;
            System.out.println((Messages.EMPTY_FILE));
        }

        return isFileEmpty;
    }

    /**
     * Get tasks in the file and
     * execute command to store tasks.
     *
     * @param command   Command object that executes the load task command.
     * @param tasksList ArrayList of tasks in the file.
     */
    public void storeTasks(Command command, ArrayList<String> tasksList) {
        for (String task : tasksList) {
            ArrayList<String> parsedTask = new StorageParser().parseLoadTask(task);
            command.executeLoadTaskCommand(parsedTask);
        }

        System.out.println(Messages.SUCCESS_LOAD_TASK);
    }

    /**
     * Saves tasks to the file.
     */
    public void saveTasks() {
        ArrayList<Task> tasksList = Command.getTasks();
        String item = "";

        try {
            FileWriter tasksFileWriter = new FileWriter(tasksFile);
            BufferedWriter bufferedWriter = new BufferedWriter(tasksFileWriter);

            for (Task task : tasksList) {
                item = new StorageParser().parseSaveTask(task);

                bufferedWriter.write(item);
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
