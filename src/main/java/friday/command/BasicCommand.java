package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

import java.util.ArrayList;


public class BasicCommand extends Command {
    public BasicCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        String action = this.getAction();
        if (action.compareTo("unmark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            return(TaskList.unmark(index));
        } else if (action.compareTo("mark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            return(TaskList.mark(index));
        } else if (action.compareTo("list") == 0) {
            String listString = String.join("\n", TaskList.returnList());
            return listString;
        } else if (action.compareTo("find") == 0) {
            String toSearch = this.getDescription();
            String listString = String.join("\n", TaskList.returnFilteredList(toSearch));
            return listString;
        }
        return ("please input an available action");
    }
}