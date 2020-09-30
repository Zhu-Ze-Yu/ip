package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * ClearCommand program removes all tasks in the list
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class ClearCommand {

    /**
     * This method removes all tasks in the list
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    public static void clear(ArrayList<Task> tasks, ArrayList<String> texts, ArrayList<Task> backupList) {
        backupList.addAll(tasks);
        tasks.clear();
        texts.clear();
        System.out.println("     Good job! You have finished all tasks in the list");
    }
}
