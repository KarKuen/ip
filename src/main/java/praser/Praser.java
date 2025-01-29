package praser;

import dukeexceptions.DukeException;

import java.util.Arrays;
import java.util.List;

public class Praser {
    //list of allowable text inputs
    private static List<String> availableActions = Arrays.asList(
            "list", "mark", "unmark", "bye", "todo", "deadline", "event");
    private static List<String> actionsWithDescription = Arrays.asList(
            "mark", "unmark", "todo", "deadline", "event");

    /**
     * Checks if the user input is a valid action within availableActions and actionsWithDescription.
     * @param input The user input.
     * @throws DukeException If the user input is not an action within availableActions.
     */
    public static void checkInput(String input) throws DukeException {
        if(input.split(" ").length <= 1) {
            String action = input.split(" ")[0];
            if(!availableActions.contains(action)) {
                throw new DukeException("please input a valid action");
            } else {
                //check if there exist a description
                if ((input.split(" ").length <= 1) &&
                        //check if the action requires a description
                        (actionsWithDescription.contains(input.split(" ")[0]))) {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }

        }
    }
}