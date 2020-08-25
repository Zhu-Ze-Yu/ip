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

        String[] sentences = new String[100];
        for(int i=0; i>=0; i++) {
            Scanner in = new Scanner(System.in);
            String words = in.nextLine();
            sentences[i] = words;
            printLine();

            if (words.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printLine();
                return;
            }

            else if (words.equals("list")) {
                int num = 1;
                for (String sentence: sentences) {
                    if (sentence.equals("list")) break;
                    System.out.println("     " + num + ". " + sentence);
                    num++;
                }
                printLine();
                i--;
            }

            else {
                System.out.println("     added: " + words);
                printLine();
            }
        }
    }
}
