package friday.command;
import java.time.LocalDateTime;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.tasks.DeadlineTask;
import friday.tasks.EventTask;
import friday.tasks.TodoTask;
import friday.ui.Ui;

/**
 * The AddCommand class represents the user command to add new tasks into the TaskList.
 */
public class AddCommand extends Command {
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        String action = this.getAction();
        if (action.compareTo("todo") == 0) {
            return (TaskList.addToList(new TodoTask(this.getDescription())));
        } else if (action.compareTo("deadline") == 0) {
            return (TaskList.addToList(createDeadLineTask()));
        } else if (action.compareTo("event") == 0) {
            return (TaskList.addToList(createEventTask()));
        }
        return ("please input one of the available actions");
    }

    /**
     * Creates a DeadLineTask if the format is correct.
     * @return The newly created DeadLineTask.
     * @throws FridayException The error when the String input fails to follow the correct format.
     */
    public DeadlineTask createDeadLineTask() throws FridayException {
        // separates text into 1)description and 2)date+time
        String[] dates = this.getDescription().split("/by ", 2);

        try {
            // checks if the user input date follows a valid format, and add it into allTasks if it is valid
            LocalDateTime date = DeadlineTask.createDateFormatted(dates[1]);
            return (new DeadlineTask(dates[0], date));
        } catch (FridayException e) {
            // if the user input date has an invalid format, do not add it into allTasks
            throw new FridayException("please input a valid date");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FridayException("please don't forget to include the /by date");
        }
    }

    /**
     * Creates a EventTask if the format is correct.
     * @return
     */
    public EventTask createEventTask() throws FridayException {
        try {
            String[] activity = this.getDescription().split("/from", 2);
            String[] period = activity[1].split("/to", 2);
            return (new EventTask(activity[0], period[0], period[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FridayException("please don't forget to include the /from and /to dates ");
        }
    }
}
