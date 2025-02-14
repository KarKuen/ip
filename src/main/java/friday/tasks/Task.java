package friday.tasks;

//an abstract task class.
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public String priority;

    public static final char OPENBRACKET = '[';
    public static final char CLOSEBRACKET = ']';
    public static final String ENDINGBRACKET = ")";
    public static final char DESCRIPTIONDIVIDER = ' ';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = "none";
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

    public String getPriority() {
        return this.priority;
    }

    public String setPriority(String priority) {
        if (priority.compareTo("high") == 0) {
            this.priority = "high";
        } else if (priority.compareTo("medium") == 0) {
            this.priority = "medium";
        } else if (priority.compareTo("low") == 0) {
            this.priority = "low";
        } else {
            return("please input either high/medium/low priority");
        }
        return("Prioritised " + this.toString() + " as " + priority);
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
