package friday.parser;

import java.util.Arrays;
import java.util.List;

import friday.command.AddCommand;
import friday.command.BasicCommand;
import friday.command.Command;
import friday.command.DeleteCommand;
import friday.command.ExitCommand;
import friday.fridayexceptions.FridayException;

/**
 * The Parser class interprets user input and performs the necessary actions.
 */
public class Parser {
    // list of allowable text inputs
    private static List<String> availableActions = Arrays.asList(
            "list", "mark", "unmark", "bye", "todo", "deadline", "event", "plist", "prioritise");
    private static List<String> actionsWithDescription = Arrays.asList(
            "mark", "unmark", "todo", "deadline", "event");

    /**
     * Checks if the user input is a valid action within availableActions and actionsWithDescription,
     * creating the relevant Command Object according to the task's action.
     * @param fullCommand The user input.
     * @throws FridayException If the user input is not an action within availableActions or if the input in invalid.
     */
    public static Command parse(String fullCommand) throws FridayException {
        String action = fullCommand.split(" ")[0];
        if (!availableActions.contains(action)) {
            throw new FridayException("please input a valid action");
        }
        // check if there exist a description && check if the action requires a description
        if ((fullCommand.split(" ").length <= 1)
                && (actionsWithDescription.contains(action))) {
            throw new FridayException("please provide a description for your action");
        }

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
