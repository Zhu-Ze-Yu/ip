package duke.ui;

/**
 * Ui program deals with interactions with the user
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-08-21
 */
public class Ui {

    /**
     * This method prints a horizontal line to divide input and output
     *
     * @return Nothing
     */
    public static void printLine() {
        System.out.print("     -");
        for (int i = 0; i < 60; i++) System.out.print('-');
        System.out.println("-");
    }

    /**
     * This method prints the greet words
     *
     * @return Nothing
     */
    public static void greetWords() {
        printLine();
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }
}