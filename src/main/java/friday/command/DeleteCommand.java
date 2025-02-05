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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        int index = Integer.parseInt(this.getAction().split(" ")[1]) - 1;
        TaskList.delete(index);
    }
}
