package command;

import dukeexceptions.DukeException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

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
