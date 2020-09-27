package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class ListCommand {

    // method to list the task
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("     You have not added any task into your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.print("\n     " + i + ".");
                System.out.println(tasks.get(i - 1).toString());
            }
        }
    }
}