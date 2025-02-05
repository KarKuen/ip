package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

public class BasicCommand extends Command {
    public BasicCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        String action = this.getAction();
        if (action.compareTo("unmark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            TaskList.unmark(index);
        } else if (action.compareTo("mark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            TaskList.mark(index);
        } else if (action.compareTo("list") == 0) {
            Ui.printList(TaskList.returnList());
        } else if (action.compareTo("find") == 0) {
            String toSearch = this.getDescription();
            Ui.printList(TaskList.returnFilteredList(toSearch));
        }
    }
}