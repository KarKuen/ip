package friday.command;

import friday.dukeexceptions.DukeException;
import friday.storage.Storage;
import friday.taskList.TaskList;
import friday.ui.Ui;

public class BasicCommand extends Command {
    public BasicCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String action = this.getAction();
        if (action.compareTo("unmark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            TaskList.unmark(index);
        } else if (action.compareTo("mark") == 0) {
            int index = Integer.parseInt(this.getDescription()) - 1;
            TaskList.mark(index);
        } else if (action.compareTo("list") == 0) {
            Ui.printList(TaskList.returnList());
        }
    }
}