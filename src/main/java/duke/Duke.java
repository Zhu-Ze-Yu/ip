package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public static final int TODO_CMD_LEN = 5;               // length of "todo "
    public static final int DEADLINE_CMD_LEN = 9;           // length of "deadline "
    public static final int EVENT_CMD_LEN = 6;              // length of "event "
    public static final int DELETE_CMD_LEN = 7;             // length of "delete "
    public static final int DONE_CMD_LEN = 5;               // length of "done "

    public static void main(String[] args) {
        Ui.greetWords();
        ArrayList<Task> tasks = new ArrayList<>();      // store tasks the user is adding
        ArrayList<String> texts = new ArrayList<>();    // store text format of each task

        try {
            File.getFileContents(File.FILE_PATHWAY, tasks, texts);
        } catch (FileNotFoundException e) {
            System.out.println("     File not found");
            Ui.printLine();
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! The date in the file need to be in the format of yyyy-mm-dd.");
            Ui.printLine();
        }

        String words = " ";
        while(!words.equals("bye")) {
            Scanner in = new Scanner(System.in);
            words = in.nextLine();
            words = words.trim();
            Ui.printLine();
            try {
                Commands(tasks, texts, words);
                File.writeToFile(File.FILE_PATHWAY, texts);
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IOException e) {
                System.out.println("     Something went wrong: " + e.getMessage());
            }
            Ui.printLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        Ui.printLine();
    }//end main

    // different commands user give
    public static void Commands(ArrayList<Task> tasks, ArrayList<String> texts, String words) throws DukeException {
        if (words.equals("list")) {
            ListCommand.listTasks(tasks);
        } else if (words.startsWith("todo")) {
            AddCommand.addTodo(tasks, texts, words);
        } else if (words.startsWith("deadline")) {
            AddCommand.addDeadline(tasks, texts, words);
        } else if (words.startsWith("event")) {
            AddCommand.addEvent(tasks, texts, words);
        } else if (words.startsWith("delete")) {
            DeleteCommand.removeTask(tasks, texts, words);
        } else if (words.startsWith("done")) {
            DoCommand.doTask(tasks, texts, words);
        } else if (words.startsWith("find")) {
            FindCommand.findName(tasks, words);
        } else if (!words.equals("bye")){
            throw new DukeException();
        }
    }
}
