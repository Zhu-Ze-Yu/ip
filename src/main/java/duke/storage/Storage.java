package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File program deals with loading tasks from the file and saving tasks in the file
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-08-21
 */
public class Storage {

    public static final String FILE_PATHWAY = "task.txt";   // file pathway

    /**
     * This method gets tasks from the file and stores in the list
     *
     * @param tasks  ArrayList of tasks
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    public static void getFileContents(ArrayList<Task> tasks, ArrayList<String> texts) {
        try {
            File f = new File(FILE_PATHWAY);    // create a File for the given file path
            Scanner s = new Scanner(f);     // create a Scanner using the File as the source
            while(s.hasNext()) {
                String words = s.nextLine();
                texts.add(words);
                String[] detail = words.split(" - ", 4);
                storeTask(tasks, detail);
            }
        } catch (FileNotFoundException e) {
            System.out.println("     File not found or you can create a txt file in your own computer and change file path");
            Ui.printLine();
        } catch (DateTimeParseException e) {
            System.out.println("     ☹ OOPS!!! Can you change dates of tasks to be yyyy-mm-dd format.");
            Ui.printLine();
        }
    }

    /**
     * This method stores each task list in the file
     *
     * @param tasks   ArrayList of tasks
     * @param detail  String array for each task in the file
     * @return Nothing
     */
    private static void storeTask(ArrayList<Task> tasks, String[] detail) {
        boolean done = false;
        if (detail[1].equals("1")) {
            done = true;
        }
        switch (detail[0]) {
            case "T":
                Task t = new Todo(detail[2]);
                t.isDone = done;
                tasks.add(t);
                break;
            case "D":
                LocalDate deadline = LocalDate.parse(detail[3]);
                Task d = new Deadline(detail[2], deadline);
                d.isDone = done;
                tasks.add(d);
                break;
            case "E":
                LocalDate event = LocalDate.parse(detail[3]);
                Task e = new Event(detail[2], event);
                e.isDone = done;
                tasks.add(e);
                break;
            default:
                break;
        }
    }

    /**
     * This method overwrites the file using the text list
     *
     * @param texts  ArrayList of tasks in the TXT format
     * @return Nothing
     */
    public static void writeToFile(ArrayList<String> texts) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATHWAY);
        for (String text : texts) {
            fw.write(text + "\n");
        }
        fw.close();
    }
}