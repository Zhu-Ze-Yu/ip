package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * ListCommand program lists all the tasks in the list
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-08-21
 */
public class ListCommand {

    /**
     * This method prints all tasks in the list
     *
     * @param tasks  ArrayList of tasks
     * @return Nothing
     */
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("     You have not added any task into your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.print("\n     " + i + ".");
                System.out.println(tasks.get(i - 1).toString());
            }
        }
    }
}