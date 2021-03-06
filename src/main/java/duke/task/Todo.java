package duke.task;

public class Todo extends Task {
    protected char type;

    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    //Override
    public String toString() {
        return "[T]" + super.toString();
    }
}