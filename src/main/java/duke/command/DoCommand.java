package duke.command;

import duke.Duke;
import duke.task.Task;

import java.util.ArrayList;

public class DoCommand {// do the task and mark it as done

    public static void doTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(Duke.DONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //mark as done
            System.out.println("     Nice! I've marked this task as done:");
            Task task = tasks.get(index - 1);
            task.markAsDone();
            String text = texts.get(index - 1);
            text = text.substring(0, 4) + "1" + text.substring(5);
            texts.set(index - 1, text);
            System.out.println("       " + task.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have finished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"done\".");
        }
    }
}