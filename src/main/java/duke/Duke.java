package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final int TODO_CMD_LEN = 5;               // length of "todo"
    public static final int DEADLINE_CMD_LEN = 9;           // length of "deadline"
    public static final int EVENT_CMD_LEN = 6;              // length of "event"
    public static final int DELETE_CMD_LEN = 7;             // length of "remove"
    public static final int DONE_CMD_LEN = 5;               // length of "done"

    public static void main(String[] args) {
        greetWords();

        ArrayList<Task> tasks = new ArrayList<>();
        String words = " ";
        while(!words.equals("bye")) {
            Scanner in = new Scanner(System.in);
            words = in.nextLine();
            words = words.trim();
            printLine();
            try {
                Commands(tasks, words);
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            printLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }//end main


    // different commands user give
    private static void Commands(ArrayList<Task> tasks, String words) throws DukeException {
        if (words.equals("list")) {
            listTasks(tasks);
        } else if (words.startsWith("todo")) {
            addTodo(tasks, words);
        } else if (words.startsWith("deadline")) {
            addDeadline(tasks, words);
        } else if (words.startsWith("event")) {
            addEvent(tasks, words);
        } else if (words.startsWith("delete")) {
            removeTask(tasks, words);
        } else if (words.startsWith("done")) {
            doTask(tasks, words);
        } else if (!words.equals("bye")){
            throw new DukeException();
        }
    }

    // print the horizontal line
    public static void printLine() {
        System.out.print("     -");
        for(int i=0; i<60; i++) System.out.print('-');
        System.out.println("-");
    }

    // greet word for duke.Duke
    public static void greetWords() {
        printLine();
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }

    // method to list the task
    public static void listTasks(ArrayList<Task> tasks) {
        if(tasks.size() == 0) {
            System.out.println("     You have not added any task into your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.print("\n     " + i + ".");
                System.out.println(tasks.get(i-1).toString());
            }
        }
    }

    // print the add task message
    public static void addTask(Task t, int amount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t.toString());
        System.out.println("     Now you have " +  amount + " tasks in the list.");
    }

    // method to add todo task
    private static void addTodo(ArrayList<Task> tasks, String words) {
        try {
            String name = words.substring(TODO_CMD_LEN);
            Task task = new Todo(name);
            tasks.add(task);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    // method to add deadline task
    private static void addDeadline(ArrayList<Task> tasks, String words) {
        try {
            String[] detail = words.split("/by");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();

            Task task = new Deadline(detail[0].substring(DEADLINE_CMD_LEN),detail[1]);
            tasks.add(task);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a task with deadline cannot be empty.");
        }
    }

    // method to add event task
    private static void addEvent(ArrayList<Task> tasks, String words) {
        try {
            String[] detail = words.split("/at");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();

            Task task = new Event(detail[0].substring(EVENT_CMD_LEN), detail[1]);
            tasks.add(task);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    // remove the task the user don't want to keep
    private static void removeTask(ArrayList<Task> tasks, String words) {
        try {
            String taskIndex = words.substring(DELETE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //delete the task
            System.out.println("     Noted. I've removed this task:");
            Task t = tasks.get(index-1);
            t.markAsDone();
            System.out.print("       ");
            System.out.println(t.toString());
            tasks.remove(index-1);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you want to remove.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"delete\".");
        }
    }

    // do the task and mark it as done
    private static void doTask(ArrayList<Task> tasks, String words) {
        try {
            String taskIndex = words.substring(DONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);

            //mark as done
            System.out.println("     Nice! I've marked this task as done:");
            Task t = tasks.get(index-1);
            t.markAsDone();
            System.out.print("       ");
            System.out.println(t.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have finished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"done\".");
        }
    }
}