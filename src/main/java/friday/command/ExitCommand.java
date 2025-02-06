package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            //save allTasks into TaskList file
            Storage.saveFile(TaskList.returnList());
            return(ui.bidFarewell());
        } catch (IOException e){
            //unable to save allTasks into TaskList file
            System.out.println("Error saving TaskList");
        } finally {
            isExit = true;
        }
        return("");
    }
}
