package friday.dukeexceptions;

public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
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