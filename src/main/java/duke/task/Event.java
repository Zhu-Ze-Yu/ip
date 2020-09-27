package duke.task;

public class Event extends Task {
    public String at;
    protected char type;

    public Event(String description, String at) {
        super(description);
        this.type = 'E';
        this.at = at;
    }

    //Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
