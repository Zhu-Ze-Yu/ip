package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * UndoCommand program uodo the task
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class UndoCommand {

    public static final int UNDONE_CMD_LEN = 7;       // length of "undone "

    /**
     * This method prints the message of unfinishing the task
     * and uses method markAsUndone to show the task is unfinished
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param index  Index of the task user want to unfinish in the list
     * @return Nothing
     */
    private static void printMessage(ArrayList<Task> tasks, ArrayList<String> texts, int index) {
        System.out.println("     Noted! I've marked this task as undone:");
        Task task = tasks.get(index - 1);
        task.markAsUndone();
        String text = texts.get(index - 1);
        // mark the task in the file also as done (e.g., change 0 to 1)
        text = text.substring(0, 4) + "0" + text.substring(5);
        texts.set(index - 1, text);
        System.out.println("       " + task.toString());
    }

    /**
     * This method gets the index of the task user want unfinish
     * Unfinishes the task and print the message by using method printMessage
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void undoTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(UNDONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //mark as undone
            printMessage(tasks, texts, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have unfinished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"undone\".");
        }
    }
}