package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate by;
    protected char type;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.type = 'D';
        this.by = by;
    }

    //Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}

