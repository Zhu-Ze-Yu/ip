package duke.command;

import duke.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand {

    // print the add task message
    public static void addTask(Task t, int amount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t.toString());
        System.out.println("     Now you have " + amount + " tasks in the list.");
    }

    // method to add todo task
    public static void addTodo(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Todo task = Parser.parseTodo(words);
            tasks.add(task);
            texts.add("T - 0 - " + task.description);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    // method to add deadline task
    public static void addDeadline(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Deadline task = Parser.parseDeadline(words);
            tasks.add(task);
            texts.add("D - 0 - " + task.description + " - " + task.by);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a task with deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! The date need to be in the format of yyyy-mm-dd.");
        }
    }

    // method to add event task
    public static void addEvent(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            Event task = Parser.parseEvent(words);
            tasks.add(task);
            texts.add("E - 0 - " + task.description + " - " + task.at);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! The date need to be in the format of yyyy-mm-dd.");
        }
    }
}

