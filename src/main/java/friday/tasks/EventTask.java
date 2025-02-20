package friday.tasks;

/**
 * The EventTask class represents a task with a time frame.
 */
public class EventTask extends Task {
    public static final String EVENT_TYPE = String.valueOf(OPEN_BRACKET + "E" + CLOSE_BRACKET);
    public static final String FROM_FORMAT_STRING = " (from:";
    public static final String TO_FORMAT_STRING = " to: ";

    protected String from;
    protected String to;
    /**
     * Initialises a newly created EventTask object with a description, starting time period, and ending time period.
     * @param description The description of the task.
     * @param from The starting time period of the task.
     * @param to The ending time period of the task.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return (EVENT_TYPE
                + super.toString()
                + FROM_FORMAT_STRING
                + from
                + TO_FORMAT_STRING
                + to
                + ENDING_BRACKET);
    }
}
