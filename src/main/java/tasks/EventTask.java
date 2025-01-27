package tasks;

//an event task with a start and end date, indicated by the "from" and "to" variables.
public class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from:" + from + "to:" + to + ")");
    }
}
