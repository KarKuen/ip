package command;

import dukeexceptions.DukeException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

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

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}

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