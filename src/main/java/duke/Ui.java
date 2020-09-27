package duke;

public class Ui {

    // print the horizontal line
    public static void printLine() {
        System.out.print("     -");
        for (int i = 0; i < 60; i++) System.out.print('-');
        System.out.println("-");
    }

    // greet word for duke.Duke
    public static void greetWords() {
        printLine();
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }
}