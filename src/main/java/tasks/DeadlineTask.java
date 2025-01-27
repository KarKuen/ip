package tasks;

//a deadline task with a deadline, indicated by the "by" variable.
public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by:" + by + ")");
    }
}
