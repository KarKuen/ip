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
            int index = Integer.parseInt(this.getDescription()) - 1;
            return (TaskList.unmark(index));
        } else if (action.compareTo("mark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            return (TaskList.mark(index));
        } else if (action.compareTo("list") == 0) {
            return String.join("\n", TaskList.returnList());
        } else if (action.compareTo("find") == 0) {
            String toSearch = this.getDescription();
            return String.join("\n", TaskList.returnFilteredList(toSearch));
        } else if (action.compareTo("prioritise") == 0) {
            String[] description = this.getDescription().split(" ");
            assert description.length == 2 : "follow the format: prioritise taskIndex priorityLevel";
            int index = Integer.parseInt(description[0]) - 1;
            String priority = description[1];
            return (TaskList.prioritise(index, priority));
        } else if (action.equals("plist")) {
            assert this.getDescription() != null : "please include the level of priority";
            return String.join("\n", TaskList.returnPriorityList(this.getDescription()));
        }
        return ("please input an available action");
    }
}
