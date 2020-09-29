package duke.command;

import duke.task.Task;
import java.util.ArrayList;

public class FindCommand {

    public static final int FIND_CMD_LEN = 5;               // length of "find "

    // method to find tasks that contain the keyword
    public static void findName(ArrayList<Task> tasks, String words) {
        ArrayList<Integer> index = new ArrayList<>();      // store index of tasks contain keyword
        String keyword = words.substring(FIND_CMD_LEN);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                index.add(i);
            }
        }

        if (index.size() == 0) {
            System.out.println("     Sorry, none of the task descriptions contain" + keyword);
        } else {
            List(tasks, index);
        }
    }

    // list the matching tasks
    private static void List(ArrayList<Task> tasks, ArrayList<Integer> index) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 1; i <= index.size(); i++) {
            System.out.print("\n     " + i + ".");
            System.out.println(tasks.get(index.get(i-1)).toString());
        }
    }
}
