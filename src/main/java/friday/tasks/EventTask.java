package friday.tasks;

//an event task with a start and end date, indicated by the "from" and "to" variables.
public class EventTask extends Task {
    protected String from;
    protected String to;

    public static final String EVENTTYPE = String.valueOf(OPENBRACKET + "E" + CLOSEBRACKET);
    public static final String FROMFORMATSTRING = " (from:";
    public static final String TOFORMATSTRING = " to: ";

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
