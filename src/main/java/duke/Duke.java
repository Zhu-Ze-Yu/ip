package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.storage.File;
import duke.task.Task;
import duke.ui.Ui;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Duke program stores Todo Deadline and Event three types of tasks
 * Add new tasks
 * Delete tasks
 * Do tasks
 * Find tasks with keyword
 * List tasks in the list
 * Taskslist is used for storing tasks inside program e.g., <code>[T][✓] a</code>
 * Textlist is used for storing tasks inside TXT file e.g., <code>T - 1 - a</code>
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-08-21
 */
public class Duke {

    /**
     * This method makes use of getFileContents to load tasks from the file
     * and saving tasks in the file by using writeToFile method
     * Run the whole program by using Commands method
     *
     * @return Nothing
     */
    public static void main(String[] args) {
        Ui.greetWords();
        ArrayList<Task> tasks = new ArrayList<>();      // store tasks the user is adding
        ArrayList<String> texts = new ArrayList<>();    // store text format of each task

        File.getFileContents(File.FILE_PATHWAY,tasks, texts);

        // if the user say bye then exit become true
        boolean exit = false;
        while(!exit) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine();
            words = words.trim();
            Ui.printLine();
            try {
                exit = Commands(tasks, texts, words);
                File.writeToFile(File.FILE_PATHWAY, texts);
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IOException e) {
                System.out.println("     Something went wrong: " + e.getMessage());
            }
            Ui.printLine();
        }
    }//end main

    /**
     * This method distinguishes different kinds of command user typed in
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static boolean Commands(ArrayList<Task> tasks, ArrayList<String> texts, String words) throws DukeException {
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
        } else if (words.equals("help")) {
            HelpCommand.printHelpList();
        } else if (words.equals("bye")) {
            Ui.exitMessage();
            return true;
        } else {
            throw new DukeException();
        }
        return false;
    }
}