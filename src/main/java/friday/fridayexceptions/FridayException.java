package friday.fridayexceptions;

/**
 * The FridayException class are exceptions unique to Friday.
 */
public class FridayException extends Exception {
    private String message;

    /**
     * Initialises a newly created FridayException object with the appropriate description message.
     * @param message The description of the error.
     */
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
