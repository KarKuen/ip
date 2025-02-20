package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

/**
 * The BasicCommand class represents commands that do not end the program and commands that do not add or delete.
 */
public class BasicCommand extends Command {
    public BasicCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        String action = this.getAction();
        if (action.compareTo("unmark") == 0) {
            return (TaskList.unmark(checkInt(this)));
        } else if (action.compareTo("mark") == 0) {
            return (TaskList.mark(checkInt(this)));
        } else if (action.compareTo("list") == 0) {
            return String.join("\n", TaskList.returnList());
        } else if (action.compareTo("find") == 0) {
            String toSearch = this.getDescription();
            return String.join("\n", TaskList.returnFilteredList(toSearch));
        } else if (action.compareTo("prioritise") == 0) {
            String[] description = checkPriority();
            int index = Integer.parseInt(description[0]) - 1;
            String priority = description[1];
            return (TaskList.prioritise(index, priority));
        } else if (action.equals("plist")) {
            String description = checkPriorityList();
            assert description != null : "please include the level of priority";
            return String.join("\n", TaskList.returnPriorityList(description));
        }
        return ("please input an available action");
    }

    /**
     * Checks if the input given is an integer.
     * @return The index if it is an integer.
     * @throws FridayException The exception if the user did not input an integer.
     */
    public static Integer checkInt(Command command) throws FridayException {
        int index;
        try {
            index = Integer.parseInt(command.getDescription()) - 1;
        } catch (NumberFormatException e) {
            throw new FridayException("please input an integer");
        }
        return index;
    }

    /**
     * Checks if the appropriate parameters were inputted.
     * @return The description if the parameters are acceptable.
     * @throws FridayException The errors if the parameters provided are unacceptable.
     */
    public String[] checkPriority() throws FridayException {
        String[] description;
        try {
            description = this.getDescription().split(" ");
            Integer index = Integer.valueOf(description[0]) - 1;
            String priority = description[1];
        } catch (NullPointerException e) {
            throw new FridayException("Please include an index and a priority");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FridayException("Please include an index and a priority");
        } catch (NumberFormatException e) {
            throw new FridayException("Please include an index and a priority");
        }
        return description;
    }

    /**
     * Check if the appropriate priority was provided.
     * @return The priority if it is an acceptable one.
     * @throws FridayException The error if the given priority is unacceptable.
     */
    public String checkPriorityList() throws FridayException {
        String priority = this.getDescription();
        if (priority == null) {
            throw new FridayException("please include an appropriate priority");
        }
        if (!((priority.compareTo("high") == 0)
                || (priority.compareTo("medium") == 0)
                || (priority.compareTo("low") == 0))) {
            throw new FridayException("Please input a high/medium/low priority");
        }
        return priority;
    }
}
