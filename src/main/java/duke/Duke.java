package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.storage.Storage;
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
        ArrayList<Task> backupList = new ArrayList<>(); // store tasks removed by the user
        Storage.getFileContents(tasks, texts);   // load tasks from file

        // if the user say bye then exit become true
        boolean exit = false;
        while(!exit) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine().trim();
            Ui.printLine();
            try {
                exit = run(tasks, texts, backupList, words);
                Storage.writeToFile(texts);    // overwrite file
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
    public static boolean run(ArrayList<Task> tasks,
                                   ArrayList<String> texts,
                                   ArrayList<Task> backupList,
                                   String words) throws DukeException {
        String command;
        if (words.contains(" ")) {
            command = words.substring(0, words.indexOf(" "));
        } else {
            command = words;
        }

        switch (command) {
            case "list":
                ListCommand.listTasks(tasks);
                break;
            case "todo":
                AddCommand.addTodo(tasks, texts, words);
                break;
            case "deadline":
                AddCommand.addDeadline(tasks, texts, words);
                break;
            case "event":
                AddCommand.addEvent(tasks, texts, words);
                break;
            case "delete":
                DeleteCommand.removeTask(tasks, texts, backupList,words);
                break;
            case "done":
                DoCommand.doTask(tasks, texts, words);
                break;
            case "undone":
                UndoCommand.undoTask(tasks, texts, words);
                break;
            case "find":
                FindCommand.findName(tasks, words);
                break;
            case "group":
                GroupCommand.group(tasks, texts);
                break;
            case "clear":
                ClearCommand.clear(tasks, texts, backupList);
                break;
            case "help":
                HelpCommand.printHelpList();
                break;
            case "restore":
                RestoreCommand.restore(backupList);
                break;
            case "bye":
                Ui.exitMessage();
                return true;
            default:
                throw new DukeException();
        }
        return false;
    }
}