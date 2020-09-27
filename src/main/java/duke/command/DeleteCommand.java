package duke.command;

import duke.Duke;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand {

    // remove the task the user don't want to keep
    public static void removeTask(ArrayList<Task> tasks, ArrayList<String> texts, String words) {
        try {
            String taskIndex = words.substring(Duke.DELETE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //delete the task
            System.out.println("     Noted. I've removed this task:");
            Task t = tasks.get(index - 1);
            System.out.println("       " + t.toString());
            tasks.remove(index - 1);
            texts.remove(index - 1);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you want to remove.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"delete\".");
        }
    }
}