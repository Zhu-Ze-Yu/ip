package duke.task;

public class Deadline extends Task {
    protected String by;
    protected char type;

    public Deadline(String description, String by) {
        super(description);
        this.type = 'D';
        this.by = by;
    }

    //Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
