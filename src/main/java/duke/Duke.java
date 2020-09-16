package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static final int TODO_CMD_LEN = 5;               // length of "todo"
    public static final int DEADLINE_CMD_LEN = 9;           // length of "deadline"
    public static final int EVENT_CMD_LEN = 6;              // length of "event"
    public static final int DELETE_CMD_LEN = 7;             // length of "delete"
    public static final int DONE_CMD_LEN = 5;               // length of "done"

    public static final String FILE_PATHWAY = "/Users/zhuzeyu/Desktop/data/taask.txt";   // file pathway

    public static void main(String[] args) {
        greetWords();
        ArrayList<Task> tasks = new ArrayList<>();      // store tasks the user is adding
        ArrayList<String> texts = new ArrayList<>();    // store text format of each task

        try {
            getFileContents(FILE_PATHWAY, tasks, texts);
        } catch (FileNotFoundException e) {
            System.out.println("     File not found");
            printLine();
        }
        String words = " ";
        while(!words.equals("bye")) {
            Scanner in = new Scanner(System.in);
            words = in.nextLine();
            words = words.trim();
            printLine();
            try {
                Commands(tasks, texts, words);
                writeToFile(FILE_PATHWAY,texts);
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            printLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }//end main


    // different commands user give
    private static void Commands(ArrayList<Task> tasks, ArrayList<String> texts, String words) throws DukeException {
        if (words.equals("list")) {
            listTasks(tasks);
        } else if (words.startsWith("todo")) {
            addTodo(tasks, texts, words);
        } else if (words.startsWith("deadline")) {
            addDeadline(tasks, texts, words);
        } else if (words.startsWith("event")) {
            addEvent(tasks, texts, words);
        } else if (words.startsWith("delete")) {
            removeTask(tasks, texts, words);
        } else if (words.startsWith("done")) {
            doTask(tasks, texts, words);
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
    private static void addTodo(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String name = words.substring(TODO_CMD_LEN);
            Task task = new Todo(name);
            tasks.add(task);
            texts.add("T - 0 - " + task.description);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    // method to add deadline task
    private static void addDeadline(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String[] detail = words.split("/by");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();
            Task task = new Deadline(detail[0].substring(DEADLINE_CMD_LEN),detail[1]);
            tasks.add(task);
            texts.add("D - 0 - " + task.description + " - " + detail[1]);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a task with deadline cannot be empty.");
        }
    }

    // method to add event task
    private static void addEvent(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String[] detail = words.split("/at");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();
            Task task = new Event(detail[0].substring(EVENT_CMD_LEN), detail[1]);
            tasks.add(task);
            texts.add("E - 0 - " + task.description + " - " + detail[1]);
            addTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    // remove the task the user don't want to keep
    private static void removeTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(DELETE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //delete the task
            System.out.println("     Noted. I've removed this task:");
            Task t = tasks.get(index-1);
            System.out.println("       " + t.toString());
            tasks.remove(index-1);
            texts.remove(index-1);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you want to remove.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"delete\".");
        }
    }

    // do the task and mark it as done
    private static void doTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(DONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);

            //mark as done
            System.out.println("     Nice! I've marked this task as done:");
            Task task = tasks.get(index-1);
            task.markAsDone();
            String text = texts.get(index-1);
            text = text.substring(0,4) + "1" + text.substring(5);
            texts.set(index-1, text);
            System.out.println("       " + task.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have finished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"done\".");
        }
    }

    private static void getFileContents(String filePath, ArrayList<Task> tasks, ArrayList<String> texts) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String words = s.nextLine();
            texts.add(words);
            String[] detail = words.split(" - ", 4);

            boolean done = false;
            if (detail[1].equals("1")) {
                done = true;
            }
            switch(detail[0]) {
                case "T":
                    Task t = new Todo(detail[2]);
                    t.isDone = done;
                    tasks.add(t);
                    break;
                case "D":
                    Task d = new Deadline(detail[2], detail[3]);
                    d.isDone = done;
                    tasks.add(d);
                    break;
                case "E":
                    Task e = new Event(detail[2], detail[3]);
                    e.isDone = done;
                    tasks.add(e);
                    break;
                default:
                    break;
            }
        }
    }

    private static void writeToFile(String filePath, ArrayList<String> texts) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String text : texts) {
            fw.write(text + "\n");
        }
        fw.close();
    }
}