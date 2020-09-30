package duke.command;

/**
 * HelpCommand program prints the help list
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-30
 */
public class HelpCommand {

    /**
     * This method prints the help list
     *
     * @return Nothing
     */
    public static void printHelpList() {
        System.out.println("     Here is the Help List (words in round brackets are explanations no need to type)\n");
        System.out.println("        1. add command: you can add three types of tasks by following these format");
        System.out.println("            i)   Todo task: todo xxxx(task description)");
        System.out.println("            ii)  Deadline task: deadline xxxx(task description) /by yyyy-mm-dd");
        System.out.println("            iii) Event task: event xxxx(task description) /at yyyy-mm-dd\n");
        System.out.println("        2. delete command: you can remove any task in the list by following this format");
        System.out.println("            e.g. delete 1 (the first task in the will be deleted)\n");
        System.out.println("        3. do command: you can mark any task as done by following this format");
        System.out.println("            e.g. done 1 (the first task in the will be done)\n");
        System.out.println("        4. find command: you can find any task in the list by following this format");
        System.out.println("            e.g. find xxxx (the tasks that contain the keyword xxxx will be listed)\n");
        System.out.println("        5. help command: you can ask me to print the help lis by following this format");
        System.out.println("            e.g. help\n");
        System.out.println("        6. list command: you can list all tasks in the list by following this format");
        System.out.println("            e.g. list");
    }
}
