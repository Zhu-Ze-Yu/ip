package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * DoCommand program finish the task
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class DoCommand {

    public static final int DONE_CMD_LEN = 5;       // length of "done "

    /**
     * This method prints the message of finishing the task
     * and uses method markAsDone to show the task is finished
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param index  Index of the task user want to finish in the list
     * @return Nothing
     */
    private static void printMessage(ArrayList<Task> tasks,
                                     ArrayList<String> texts,
                                     int index) {
        System.out.println("     Nice! I've marked this task as done:");
        Task task = tasks.get(index - 1);
        task.markAsDone();
        String text = texts.get(index - 1);
        // mark the task in the file also as done (e.g., change 0 to 1)
        String doneText = text.substring(0, 4) + "1" + text.substring(5);
        texts.set(index - 1, doneText);
        System.out.println("       " + task.toString());
    }

    /**
     * This method gets the index of the task user want finish
     * Finishes the task and print the message by using method printMessage
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void doTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(DONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //mark as done
            printMessage(tasks, texts, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have finished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"done\".");
        }
    }
}