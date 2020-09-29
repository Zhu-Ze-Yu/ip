package duke.command;

import duke.Duke;
import duke.task.Task;
import java.util.ArrayList;

/**
 * DeleteCommand program remove the task from both the taskslist and textslist
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class DeleteCommand {

    /**
     * This method prints the message of finishing removing one task
     * and remove the task in both taskslist and textslist
     * Shows current amount of tasks in the list
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param index  Index of the task user want to remove in the list
     * @return Nothing
     */
    private static void printMessage(ArrayList<Task> tasks, ArrayList<String> texts, int index) {
        System.out.println("     Noted. I've removed this task:");
        Task t = tasks.get(index - 1);
        System.out.println("       " + t.toString());
        tasks.remove(index - 1);
        texts.remove(index - 1);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * This method gets the index of the task user want remove
     * Removes the task and shows message by using method printMessage
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void removeTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(Duke.DELETE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            printMessage(tasks, texts, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you want to remove.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"delete\".");
        }
    }
}