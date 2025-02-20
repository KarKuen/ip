package friday.tasks;

/**
 * The TodoTask class represents a task that needs to be done.
 */
public class TodoTask extends Task {
    public static final String EVENT_TYPE = String.valueOf(OPEN_BRACKET + "T" + CLOSE_BRACKET);

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return (EVENT_TYPE
                + super.toString());
    }
}
