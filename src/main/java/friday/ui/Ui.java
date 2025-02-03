package friday.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public Ui() {}

    /**
     * Returns a String with padding above and below.
     * @param message String to be padded.
     * @return Padded String.
     */
    public static String formatString(String message) {
        return ("____________________________________\n" + message + "\n____________________________________\n");
    }

    /**
     * Returns an introductory greeting.
     * @return The introductory greet.
     */
    public static void greet() {
        System.out.print(formatString("Hello ! I'm friday.Friday\n" + "What can I do for you?"));
    }

    /**
     * Returns a farewell message.
     * @return The farewell message.
     */
    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out every item in allTasks with paddings above and below.
     * @param allTasks The list to be printed out.
     */
    public static void printList(ArrayList allTasks) {
        int counter = 1;
        System.out.println("Here are the friday.tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(counter + "." + allTasks.get(i).toString());
            counter++;
        }
    }

    public String showLoadingError(String message) {
        return (message);
    }

    public String readCommand(Scanner in) {
        String input = in.nextLine(); //new task
        return input;
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public static void showLine() {
        System.out.println("____________________________________");
    }

    public static void showUpdate(String message) {
        System.out.println(message);
    }

}