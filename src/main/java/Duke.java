import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.print("     -");
        for(int i=0; i<100; i++) System.out.print('-');
        System.out.println("-");
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();

        String[] taskName = new String[100];
        String[] taskStatus = new String[100];
        int countTask = 0;
        for(int i=0; i<100; i++) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine();
            printLine();

            if (words.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printLine();
                return;
            }

            else if (words.equals("list")) {
                System.out.println("     Here are the tasks in your list:");

                int taskNum;
                for (int j=0; j<countTask; j++) {
                    taskNum = j + 1;
                    System.out.println("     " + taskNum + ".[" + taskStatus[j] + "] " + taskName[j]);
                }
                printLine();
            }

            else if (words.startsWith("done") && words.length() ==6) {
                System.out.println("     Nice! I've marked this task as done:");
                String taskIndex = words.substring(5,6);
                int index = Integer.parseInt(taskIndex);
                taskStatus[index-1] = "\u2713";
                System.out.println("       " + "[\u2713] " + taskName[index-1]);
            }

            else {
                taskName[countTask] = words;
                taskStatus[countTask] = "\u2718";
                countTask++;

                System.out.println("     added: " + words);
                printLine();
            }
        }
    }
}
