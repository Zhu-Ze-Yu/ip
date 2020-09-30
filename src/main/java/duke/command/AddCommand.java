package duke.command;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * AddCommand program adds three different types of tasks into taskslist and textslist
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-08-21
 */
public class AddCommand {

    /**
     * This method prints the message of finishing adding one task
     * and shows current amount of tasks in the list
     *
     * @param t  Task added
     * @param amount  Number of tasks in the list currently
     * @return Nothing
     */
    public static void printMessage (Task t, int amount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t.toString());
        System.out.println("     Now you have " + amount + " tasks in the list.");
    }

    /**
     * This method stores the Todo task into both taskslist and textslist
     * and makes use of printMessage method to print the message
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void addTodo (ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Todo task = Parser.parseTodo(words);
            tasks.add(task);
            texts.add("T - 0 - " + task.description);
            printMessage(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * This method stores the Deadline task into both taskslist and textslist
     * and makes use of printMessage method to print the message
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void addDeadline(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Deadline task = Parser.parseDeadline(words);
            tasks.add(task);
            texts.add("D - 0 - " + task.description + " - " + task.by);
            printMessage(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a task with deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! The date need to be in the format of yyyy-mm-dd.");
        }
    }

    /**
     * This method stores the Event task into both taskslist and textslist
     * and makes use of printMessage method to print the message
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static void addEvent(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Event task = Parser.parseEvent(words);
            tasks.add(task);
            texts.add("E - 0 - " + task.description + " - " + task.at);
            printMessage(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! The date need to be in the format of yyyy-mm-dd.");
        }
    }
}