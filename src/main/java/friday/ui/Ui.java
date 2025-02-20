package friday.ui;

/**
 * The Ui class represents the user interface for the chatbot.
 */
public class Ui {

    public Ui() {}

    /**
     * Returns a farewell message.
     * @return The farewell message.
     */
    public static String bidFarewell() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a message describing the error.
     * @param message The description of the error.
     * @return The error message.
     */
    public String showLoadingError(String message) {
        return (message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

}
