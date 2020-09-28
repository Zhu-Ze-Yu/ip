package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class File {

    public static final String FILE_PATHWAY = "/Users/zhuzeyu/Desktop/data/task.txt";   // file pathway

    public static void getFileContents(java.lang.String filePath, java.util.ArrayList<duke.task.Task> tasks, java.util.ArrayList<java.lang.String> texts) throws java.io.FileNotFoundException {
        java.io.File f = new java.io.File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while(s.hasNext()) {
            String words = s.nextLine();
            texts.add(words);
            String[] detail = words.split(" - ", 4);
            LocalDate date = LocalDate.parse(detail[3]);

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
                    Task d = new Deadline(detail[2], date);
                    d.isDone = done;
                    tasks.add(d);
                    break;
                case "E":
                    Task e = new Event(detail[2], date);
                    e.isDone = done;
                    tasks.add(e);
                    break;
                default:
                    break;
            }
        }

    }

    public static void writeToFile(String filePath, ArrayList<String> texts) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String text : texts) {
            fw.write(text + "\n");
        }
        fw.close();
    }
}