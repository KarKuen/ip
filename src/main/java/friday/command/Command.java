package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

public abstract class Command {
    private String action;
    private String description;
    boolean isExit;

    public Command(String fullCommand) {
        try {
            String action = fullCommand.split(" ", 2)[0];
            String description = fullCommand.split(" ", 2)[1];
            this.action = action;
            this.description = description;
            this.isExit = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            this.action = fullCommand;
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {}

    public String getAction() {
        return this.action;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isExit() {
        return this.isExit;
    }
}