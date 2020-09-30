package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * FindCommand program finds a task by searching for a keyword in the list
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-18
 */
public class FindCommand {

    public static final int FIND_CMD_LEN = 5;               // length of "find "

    /**
     * This method finds all tasks contain the keyword
     * and store these tasks' index in the ArrayList index
     * Makes use of method List to print the qualified tasks
     * If there is not any task contain the keyword then print the cannot find message
     *
     * @param tasks  ArrayList of tasks
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void findName(ArrayList<Task> tasks, String words) {
        ArrayList<Integer> index = new ArrayList<>();      // store index of tasks contain keyword
        String keyword = words.substring(FIND_CMD_LEN);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                index.add(i);
            }
        }

        if (index.size() == 0) {
            System.out.println("     Sorry, none of the task descriptions contain" + keyword);
        } else {
            list(tasks, index);
        }
    }

    /**
     * This method prints the qualified tasks
     *
     * @param tasks  ArrayList of tasks
     * @param index  Index of qualified tasks
     * @return Nothing
     */
    private static void list(ArrayList<Task> tasks, ArrayList<Integer> index) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 1; i <= index.size(); i++) {
            System.out.print("\n     " + i + ".");
            System.out.println(tasks.get(index.get(i-1)).toString());
        }
    }
}