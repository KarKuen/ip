package friday.parser;

import friday.command.*;
import friday.fridayexceptions.FridayException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    //list of allowable text inputs
    private static List<String> availableActions = Arrays.asList(
            "list", "mark", "unmark", "bye", "todo", "deadline", "event");
    private static List<String> actionsWithDescription = Arrays.asList(
            "mark", "unmark", "todo", "deadline", "event");

    /**
     * Checks if the user input is a valid action within availableActions and actionsWithDescription,
     * creating the relevant Command Object according to the task's action.
     * @param fullCommand The user input.
     * @throws FridayException If the user input is not an action within availableActions or if the input in invalid.
     */
    public static Command parse(String fullCommand) throws FridayException {
        if(fullCommand.split(" ").length <= 1) {
            String action = fullCommand.split(" ")[0];
            if(!availableActions.contains(action)) {
                throw new FridayException("please input a valid action");
            } else {
                //check if there exist a description
                if ((fullCommand.split(" ").length <= 1) &&
                        //check if the action requires a description
                        (actionsWithDescription.contains(fullCommand.split(" ")[0]))) {
                    throw new FridayException("please provide a description for your action");
                }
            }
        }
        //Happy Path
        String action = fullCommand.split(" ")[0];
        if ((action.compareTo("todo") == 0)
                || (action.compareTo("deadline") == 0)
                || (action.compareTo("event") == 0)) {
            return new AddCommand(fullCommand);
        } else if ((action.compareTo("delete") == 0)) {
            return new DeleteCommand(fullCommand);
        } else if ((action.compareTo("bye") == 0)) {
            return new ExitCommand(fullCommand);
        } else {
            return new BasicCommand(fullCommand);
        }
    }
}