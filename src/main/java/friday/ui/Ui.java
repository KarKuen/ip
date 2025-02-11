package friday.ui;

public class Ui {

    public Ui() {}

    /**
     * Returns a farewell message.
     * @return The farewell message.
     */
    public static String bidFarewell() {
        return("Bye. Hope to see you again soon!");
    }

    public String showLoadingError(String message) {
        return (message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

}