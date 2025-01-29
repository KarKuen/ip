package friday.command;

import friday.dukeexceptions.DukeException;
import friday.storage.Storage;
import friday.taskList.TaskList;
import friday.ui.Ui;

public class DeleteCommand extends Command{
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.getAction().split(" ")[1]) - 1;
        TaskList.delete(index);
    }
}
