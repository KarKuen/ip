package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

public class DeleteCommand extends Command{
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        int index = Integer.parseInt(this.getDescription()) - 1;
        return(TaskList.delete(index));
    }
}
