package friday.tasks;

//a todo task.
public class TodoTask extends Task {
    public static final String EVENTTYPE = String.valueOf(OPENBRACKET + "T" + CLOSEBRACKET);

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return (EVENTTYPE
                + super.toString());
    }
}
