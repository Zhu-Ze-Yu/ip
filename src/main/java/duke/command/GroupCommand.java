package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * GroupCommand program groups all Todo tasks together in the list,
 * then followed by Deadline tasks and finally Event tasks
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class GroupCommand {

    /**
     * This method groups different kinds of tasks
     * If the task is Todo then move forward to the beginning of the list
     * If the task is Event then move backward to the end of the list
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    public static void group (ArrayList<Task> tasks, ArrayList<String> texts) {
        for (int i=0; i < texts.size(); i++) {
            if (texts.get(i).startsWith("T")) {
                moveForward(tasks, texts, i);
            }
            else if(texts.get(i).startsWith("E")) {
                moveBackward(tasks, texts, i);
            }
        }
        System.out.println("     Got it. I have group tasks in the Todo-Deadline-Event order");
    }

    /**
     * This method moves the task with the index in the list to the front
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    private static void moveForward(ArrayList<Task> tasks, ArrayList<String> texts, int index) {
        Task tempTask = tasks.get(index);
        String tempText = texts.get(index);
        tasks.remove(index);
        texts.remove(index);
        tasks.add(0,tempTask);
        texts.add(0,tempText);
    }

    /**
     * This method moves the task with the index in the list to the end
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    private static void moveBackward(ArrayList<Task> tasks, ArrayList<String> texts, int index) {
        Task tempTask = tasks.get(index);
        String tempText = texts.get(index);
        tasks.remove(index);
        texts.remove(index);
        tasks.add(tempTask);
        texts.add(tempText);
    }
}
