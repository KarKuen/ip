package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

/**
 * The Command class represents the commands that the user is able to provide to the chatbot.
 */
public abstract class Command {
    private boolean isExit;
    private String action;
    private String description;

    /**
     * Initialises a new Command object with an action, description, and exit value.
     * @param fullCommand The user input to be separated into action and description.
     */
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

    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        return ("abstract method needs to be overridden");
    }

    public String getAction() {
        return this.action;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit(boolean b) {
        this.isExit = b;
    }
}
