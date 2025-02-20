package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

/**
 * The DeleteCommand class represents the command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        return (TaskList.delete(BasicCommand.checkInt(this)));
    }
}
