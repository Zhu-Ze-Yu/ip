package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * RestoreCommand program will list all removed tasks from the backupList
 * If user want to restore, they need to type themselves
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class RestoreCommand {

    /**
     * This method list all removed tasks from the backupList
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    public static void restore(ArrayList<Task> tasks, ArrayList<String> texts, ArrayList<Task> backupList) {
        System.out.println("     Here are all tasks you have removed:");
        for (int i = 1; i <= backupList.size(); i++) {
            System.out.print("\n     " + i + ".");
            System.out.println(backupList.get(i - 1).toString());
        }
        System.out.println("\n     If you want readd these tasks, you need to redo the addcommand");
    }
}
