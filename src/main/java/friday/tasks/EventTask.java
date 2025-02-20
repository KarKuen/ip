package friday.tasks;

/**
 * The EventTask class represents a task with a time frame.
 */
public class EventTask extends Task {
    public static final String EVENTTYPE = String.valueOf(OPENBRACKET + "E" + CLOSEBRACKET);
    public static final String FROMFORMATSTRING = " (from:";
    public static final String TOFORMATSTRING = " to: ";

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
        return (EVENTTYPE
                + super.toString()
                + FROMFORMATSTRING
                + from
                + TOFORMATSTRING
                + to
                + ENDINGBRACKET);
    }
}
