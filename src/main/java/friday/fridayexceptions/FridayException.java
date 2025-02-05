package friday.fridayexceptions;

public class FridayException extends Exception {
    private String message;

    public FridayException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the message associated with the DukeException.
     * @return The message to be returned.
     */
    public String getMessage() {
        return this.message;
    }
}