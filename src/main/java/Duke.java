import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;                 // maximum amount of task input

    public static final int TODO_CMD_LEN = 5;               // length of "todo"
    public static final int DEADLINE_CMD_LEN = 9;           // length of "deadline"
    public static final int EVENT_CMD_LEN = 6;              // length of "event"
    public static final int REMOVE_CMD_LEN = 7;             // length of "remove"
    public static final int DONE_CMD_LEN = 5;               // length of "done"

    public static void main(String[] args) {
        greetWords();

        Task[] tasks = new Task[MAX_TASK];
        int countTask = 0;
        String words = " ";
        while(!words.equals("bye")) {
            Scanner in = new Scanner(System.in);
            words = in.nextLine();
            words = words.trim();
            printLine();
            try {
                countTask = Commands(tasks, countTask, words);
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            printLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }//end main

    // different commands user give
    private static int Commands(Task[] tasks, int countTask, String words) throws DukeException {
        if (words.equals("list")) {
            listTasks(tasks, countTask);
        } else if (words.startsWith("todo")) {
            countTask = addTodo(tasks, countTask, words);
        } else if (words.startsWith("deadline")) {
            countTask = addDeadline(tasks, countTask, words);
        } else if (words.startsWith("event")) {
            countTask = addEvent(tasks, countTask, words);
        } else if (words.startsWith("remove")) {
            countTask = removeTask(tasks, countTask, words);
        } else if (words.startsWith("done")) {
            doTask(tasks, words);
        } else if (!words.equals("bye")){
            throw new DukeException();
        }
        return countTask;
    }


    // print the horizontal line
    public static void printLine() {
        System.out.print("     -");
        for(int i=0; i<60; i++) System.out.print('-');
        System.out.println("-");
    }

    // greet word for Duke
    public static void greetWords() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }

    // method to list the task
    public static void listTasks(Task[] tasks,int countTask) {
        if(countTask == 0) {
            System.out.println("     You have not added any task into your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int taskIndex = 1;
            for (int i = 0; i < countTask; i++) {
                System.out.print("\n     " + taskIndex + ".");
                System.out.println(tasks[i].toString());
                taskIndex++;
            }
        }
    }

    // print the add task message
    public static void addTask(Task t, int count) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t.toString());
        count++;
        System.out.println("     Now you have " +  count + " tasks in the list.");
    }

    // method to add todo task
    private static int addTodo(Task[] tasks, int countTask, String words) {
        try {
            tasks[countTask] = new Todo(words.substring(TODO_CMD_LEN));
            addTask(tasks[countTask], countTask);
            countTask++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return countTask;
    }

    // method to add deadline task
    private static int addDeadline(Task[] tasks, int countTask, String words) {
        try {
            String[] detail = words.split("/by");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();
            tasks[countTask] = new Deadline(detail[0].substring(DEADLINE_CMD_LEN),detail[1]);
            addTask(tasks[countTask], countTask);
            countTask++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a task with deadline cannot be empty.");
        }
        return countTask;
    }

    // method to add event task
    private static int addEvent(Task[] tasks, int countTask, String words) {
        try {
            String[] detail = words.split("/at");
            detail[0] = detail[0].trim();
            detail[1] = detail[1].trim();
            tasks[countTask] = new Event(detail[0].substring(EVENT_CMD_LEN), detail[1]);
            addTask(tasks[countTask], countTask);
            countTask++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
        return countTask;
    }

    // remove the task the user don't want to keep
    private static int removeTask(Task[] tasks, int countTask, String words) {
        try {
            String task = words.substring(REMOVE_CMD_LEN);
            int totalNum = countTask;
            for (int i = 0; i < countTask; i++) {
                String t = tasks[i].description;
                if(t.equals(task)) {
                    System.out.println("     I've removed this task:");
                    System.out.println("       " + tasks[i]);
                    tasks[i] = tasks[countTask -1];
                    tasks[countTask -1] = null;
                    countTask--;
                    System.out.println("     Now you have " + countTask + " tasks in the list.");
                    break;
                }
            }
            if (totalNum == countTask) {
                throw new DoNotHaveException();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you want to remove.");
        } catch (DoNotHaveException e) {
            System.out.println("     I can't find this task in your list.");
        }
        return countTask;
    }

    // do the task and mark it as done
    private static void doTask(Task[] tasks, String words) {
        try {
            String taskIndex = words.substring(DONE_CMD_LEN);
            int index = Integer.parseInt(taskIndex);
            //mark as done
            finishTask(tasks[index - 1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("     Sorry, I don't know which task you have finished.");
        } catch (NumberFormatException e) {
            System.out.println("     Sorry, there must a number after \"done\".");
        }
    }

    // print finish task message
    public static void finishTask(Task t) {
        System.out.println("     Nice! I've marked this task as done:");
        t.markAsDone();
        System.out.print("       ");
        System.out.println(t.toString());
    }
}
