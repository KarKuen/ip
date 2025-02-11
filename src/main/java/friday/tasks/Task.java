package friday.tasks;

//an abstract task class.
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static final char OPENBRACKET = '[';
    public static final char CLOSEBRACKET = ']';
    public static final String ENDINGBRACKET = ")";
    public static final char DESCRIPTIONDIVIDER = ' ';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string indicating if the task is completed or not.
     * @return The String "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the task status.
     * @param status The boolean value to change the task's isDone variable to.
     */
    public void setTaskStatus(Boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (OPENBRACKET
                + this.getStatusIcon()
                + CLOSEBRACKET
                + DESCRIPTIONDIVIDER
                + this.description);
    }
}
