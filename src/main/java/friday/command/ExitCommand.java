package friday.command;

import friday.dukeexceptions.DukeException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            //save allTasks into TaskList file
            Storage.saveFile(TaskList.returnList());
            ui.farewell();
        } catch (IOException e){
            //unable to save allTasks into TaskList file
            System.out.println("Error saving TaskList");
        } finally {
            isExit = true;
        }
    }
}
