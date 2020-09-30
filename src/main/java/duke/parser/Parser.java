package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.time.LocalDate;

/**
 * Parser program parse the message user typed in and convert into Task format
 *
 * @author  Zhu Zeyu
 * @version 1.0
 * @since   2020-09-11
 */
public class Parser {

    public static final int TODO_CMD_LEN = 5;               // length of "todo "
    public static final int DEADLINE_CMD_LEN = 9;           // length of "deadline "
    public static final int EVENT_CMD_LEN = 6;              // length of "event "

    /**
     * This method parse the Todo task
     * e.g., </code>todo a</code> to <code>[T][✗] a</code>
     *
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static Todo parseTodo(String words) {
        String name = words.substring(TODO_CMD_LEN);
        Todo task = new Todo(name);
        return task;
    }

    /**
     * This method parse the Deadline task
     * e.g., </code>deadline a /by 2000-01-01 </code> to <code>[D][✗] a (by: Jan 01 2000)</code>
     *
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static Deadline parseDeadline(String words) {
        String[] detail = words.split("/by");
        detail[0] = detail[0].trim();
        detail[1] = detail[1].trim();
        LocalDate date = LocalDate.parse(detail[1]);
        Deadline task = new Deadline(detail[0].substring(DEADLINE_CMD_LEN), date);
        return task;
    }

    /**
     * This method parse the Event task
     * e.g., </code>event a /at 2000-01-01 </code> to <code>[E][✗] a (at: Jan 01 2000)</code>
     *
     * @param words  Words the user typed in
     * @return Nothing
     */
    public static Event parseEvent(String words) {
        String[] detail = words.split("/at");
        detail[0] = detail[0].trim();
        detail[1] = detail[1].trim();
        LocalDate date = LocalDate.parse(detail[1]);
        Event task = new Event(detail[0].substring(EVENT_CMD_LEN), date);
        return task;
    }
}