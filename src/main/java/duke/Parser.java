package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.time.LocalDate;

public class Parser {

    public static Todo parseTodo(String words) {
        String name = words.substring(Duke.TODO_CMD_LEN);
        Todo task = new Todo(name);
        return task;
    }

    public static Deadline parseDeadline(String words) {
        String[] detail = words.split("/by");
        detail[0] = detail[0].trim();
        detail[1] = detail[1].trim();
        LocalDate date = LocalDate.parse(detail[1]);
        Deadline task = new Deadline(detail[0].substring(Duke.DEADLINE_CMD_LEN), date);
        return task;
    }

    public static Event parseEvent(String words) {
        String[] detail = words.split("/at");
        detail[0] = detail[0].trim();
        detail[1] = detail[1].trim();
        LocalDate date = LocalDate.parse(detail[1]);
        Event task = new Event(detail[0].substring(Duke.EVENT_CMD_LEN), date);
        return task;
    }
}
