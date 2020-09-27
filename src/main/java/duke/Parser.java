package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

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
        Deadline task = new Deadline(detail[0].substring(Duke.DEADLINE_CMD_LEN), detail[1]);
        return task;
    }

    public static Event parseEvent(String words) {
        String[] detail = words.split("/at");
        detail[0] = detail[0].trim();
        detail[1] = detail[1].trim();
        Event task = new Event(detail[0].substring(Duke.EVENT_CMD_LEN), detail[1]);
        return task;
    }
}
