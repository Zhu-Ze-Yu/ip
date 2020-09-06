import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;
    public static final int TODO_CMD_LEN = 5;
    public static final int DEADLINE_CMD_LEN = 9;
    public static final int EVENT_CMD_LEN = 6;
    public static final int REMOVE_CMD_LEN = 7;
    public static final int DONE_CMD_LEN = 5;

    public static void main(String[] args) {
        greetWords();

        Task[] tasks = new Task[MAX_TASK];
        int countTask = 0;

        while(true) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine();
            printLine();

            if (words.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printLine();
                return;
            } else if (words.equals("list")) {
                listTasks(tasks,countTask);
            } else if (words.startsWith("todo")) {
                if (words.length() <= TODO_CMD_LEN) {
                    forgetName();
                } else {
                    //get the task description and type
                    tasks[countTask] = new Todo(words.substring(TODO_CMD_LEN));

                    addTask(tasks[countTask], countTask);
                    countTask++;
                }
            } else if (words.startsWith("deadline")) {
                if (words.length() <= DEADLINE_CMD_LEN) {
                    forgetName();
                } else if (!words.contains("/")) {
                    System.out.println("     Oh no, you forget to add the date for your \"deadline\" task ");
                    printLine();
                } else {
                    //get the task description
                    int slashIndex = words.indexOf('/');
                    tasks[countTask] = new Deadline(words.substring(DEADLINE_CMD_LEN,slashIndex-1),words.substring(slashIndex+3));
                    addTask(tasks[countTask],countTask);
                    countTask++;
                }
            } else if (words.startsWith("event")) {
                if (words.length() <= EVENT_CMD_LEN) {
                    forgetName();
                } else if (!words.contains("/")) {
                    System.out.println("     Oh no, you forget to add the time for your \"event\" task ");
                    printLine();
                } else {
                    //get the task description
                    int slashIndex = words.indexOf('/');
                    tasks[countTask] = new Event(words.substring(EVENT_CMD_LEN, slashIndex-1), words.substring(slashIndex + 3));
                    addTask(tasks[countTask], countTask);
                    countTask++;
                }
            } else if (words.startsWith("remove")) {
                if (words.length() <= REMOVE_CMD_LEN) {
                    System.out.println("     Sorry, I don't know which task you want to remove.");
                    printLine();
                } else {
                    String task = words.substring(REMOVE_CMD_LEN);
                    int totalNum = countTask;
                    for (int i = 0; i < countTask; i++) {
                        String t = tasks[i].description;
                        if(t.equals(task)) {
                            System.out.println("     I've removed this task:");
                            System.out.println("       " + tasks[i]);
                            tasks[i] = tasks[countTask-1];
                            tasks[countTask-1] = null;
                            countTask--;
                            System.out.println("     Now you have " +  countTask + " tasks in the list.");
                            printLine();
                            break;
                        }
                    }
                    if (totalNum == countTask) {
                        System.out.println("     I can't find this task in your list.");
                        printLine();
                    }
                }
            } else if (words.startsWith("done") && words.length() == DONE_CMD_LEN+1) {
                String taskIndex = words.substring(DONE_CMD_LEN, DONE_CMD_LEN+1);
                int index = Integer.parseInt(taskIndex);

                //mark as done
                finishTask(tasks[index - 1]);
            } else {
                System.out.println("     Sorry,this task name is not allowed");
                printLine();
            }
        }
    }//end main

    public static void greetWords() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }

    public static void forgetName() {
        System.out.println("     Oh no, you forget to add the name for your task.");
        printLine();
    }

    public static void printLine() {
        System.out.print("     -");
        for(int i=0; i<60; i++) System.out.print('-');
        System.out.println("-");
    }

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
        printLine();
    }

    public static void addTask(Task t, int count) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t.toString());
        count++;
        System.out.println("     Now you have " +  count + " tasks in the list.");
        printLine();
    }

    public static void finishTask(Task t) {
        System.out.println("     Nice! I've marked this task as done:");
        t.markAsDone();
        System.out.print("       ");
        System.out.println(t.toString());
        printLine();
    }
}
