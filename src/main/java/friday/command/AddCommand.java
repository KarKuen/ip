package friday.command;

import friday.fridayexceptions.FridayException;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.tasks.DeadlineTask;
import friday.tasks.EventTask;
import friday.tasks.TodoTask;
import friday.ui.Ui;

import java.time.LocalDateTime;

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
            //separates text into 1)description and 2)date+time
            String[] dates = this.getDescription().split("/by ", 2);

            try {
                //checks if the user input date follows a valid format, and add it into allTasks if it is valid
                LocalDateTime date = DeadlineTask.createDateFormatted(dates[1]);
                return (TaskList.addToList(new DeadlineTask(dates[0], date)));
            } catch (FridayException e) {
                //if the user input date has an invalid format, do not add it into allTasks
                throw new FridayException("please input a valid date");
            }
        } else if (action.compareTo("event") == 0) {
            String[] activity = this.getDescription().split("/from", 2);
            String[] period = activity[1].split("/to", 2);
            return (TaskList.addToList(new EventTask(activity[0], period[0], period[1])));
        }
        return("please input one of available actions");
    }
}
