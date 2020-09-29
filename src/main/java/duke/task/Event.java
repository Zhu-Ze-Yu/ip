package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate at;
    protected char type;

    public Event(String description, LocalDate at) {
        super(description);
        this.type = 'E';
        this.at = at;
    }

    //Override
    public String toString() {
        String date = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
