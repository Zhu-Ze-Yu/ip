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

        while(true) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine();
            printLine();

            if (words.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printLine();
                return;
            }

            System.out.println("     " + words);
            printLine();
        }
    }
}
