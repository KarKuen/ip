package friday.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import friday.fridayexceptions.FridayException;
import friday.tasks.DeadlineTask;
import friday.tasks.EventTask;
import friday.tasks.Task;
import friday.tasks.TodoTask;

/**
 * The TaskList class represents the list of tasks.
 */
public class TaskList {
    // TaskList that stores all friday.tasks
    private static ArrayList allTasks;

    /**
     * Initialises the TaskList using the user's saved file.
     * @param temporaryFile The file previously saved in the user's computer.
     */
    public TaskList(ArrayList<String> temporaryFile) {
        ArrayList<Task> convertedTemporaryFile = new ArrayList<>();
        for (int i = 0; i < temporaryFile.size(); i++) {
            String checkListItem = temporaryFile.get(i);
            if ((checkListItem.contains(TodoTask.EVENTTYPE))) {
                assert checkListItem.contains("] ") : "Todo task format is incorrect";
                String todoTask = checkListItem.split("] ")[1];
                convertedTemporaryFile.add(new TodoTask(todoTask));
            } else if ((checkListItem.contains(DeadlineTask.EVENTTYPE))) {
                assert checkListItem.contains("] ")
                        && checkListItem.contains("(by: ") : "Deadline task format is incorrect";
                String deadlineTask = checkListItem.split("] ")[1];
                String description = deadlineTask.split(" \\(")[0];
                String by = deadlineTask.split("by: ")[1].split("\\)")[0];
                try {
                    LocalDateTime checkDate = DeadlineTask.createDateFormatted(by);
                    convertedTemporaryFile.add(new DeadlineTask(description, checkDate));
                } catch (FridayException e) {
                    throw new RuntimeException(e);
                }
            } else if ((checkListItem.contains(EventTask.EVENTTYPE))) {
                assert checkListItem.contains("] ")
                        && checkListItem.contains("(from: ")
                        && checkListItem.contains(" to:") : "Event task format is incorrect";
                String eventTask = checkListItem.split("] ")[1];
                String description = eventTask.split(" \\(")[0];
                String schedule = eventTask.split("from:")[1];

                assert schedule.contains(" to:") : "Event schedule format is incorrect";
                String from = schedule.split(" to:")[0];
                String to = schedule.split("to: ")[1].split("\\)")[0];
                convertedTemporaryFile.add(new EventTask(description, from, to));
            }
        }
        this.allTasks = convertedTemporaryFile;
    }

    /**
     * Initialises a new TaskList if the user has not previously saved a file.
     */
    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    /**
     * Returns the tasks stored inside the TaskList.
     * @return The formatted strings of the tasks inside the TaskList.
     */
    public static ArrayList returnList() {
        ArrayList<String> convertedList = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); i++) {
            convertedList.add(allTasks.get(i).toString());
        }
        return convertedList;
    }

    /**
     * Returns the task stored inside the TaskList, only if they contain a specified String.
     * @param toSearch The specified String to check for within each task description.
     * @return The formatted strings of the tasks inside the TaskList, only if they contain the specified String.
     */
    public static ArrayList<String> returnFilteredList(String toSearch) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); i++) {
            if (allTasks.get(i).toString().contains(toSearch)) {
                filteredList.add(allTasks.get(i).toString());
            }
        }
        return filteredList;
    }

    /**
     * Returns the tasks stored in the TaskList, only if they are of a specified priority.
     * @param priority The specified priority level to return.
     * @return The formatted strings of the tasks inside the TaskList, only if they are of the specified priority.
     */
    public static ArrayList<String> returnPriorityList(String priority) {
        ArrayList<String> priorityList = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = (Task) allTasks.get(i);
            String taskPriority = task.getPriority();
            if (taskPriority.compareTo(priority) == 0) {
                priorityList.add(allTasks.get(i).toString());
            }
        }
        return priorityList;
    }

    /**
     * Adds a task into allTasks.
     * @param task Task to be added into allTasks.
     * @return The chatbot update message to indicate successfully adding to the list.
     */
    @SuppressWarnings("unchecked") // SuppressWarnings of adding Task task into the generic ArrayList allTasks
    public static String addToList(Task task) {
        allTasks.add(task);
        return ("Got it. I've added this task:\n" + task.toString() + "\n" + getTaskCount());
    }

    /**
     * Returns a String indicating the number of friday.tasks in allTasks.
     * @return String with the number of friday.tasks in allTasks.
     */
    public static String getTaskCount() {
        return ("Now you have " + allTasks.size() + " friday.tasks in the list.");
    }

    /**
     * Unmarks the checkbox of a task in allTasks.
     * @param index Index of the checkbox index to be unmarked.
     * @return The chatbot update message to indicate successfully unmarking the task.
     */
    public static String unmark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(false);
        return ("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Marks the checkbox of a task in allTasks.
     * @param index Index of the checkbox index to be marked.
     * @return The chatbot update message to indicate successfully marking the task.
     */
    public static String mark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(true);
        return ("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Deletes a task in allTasks.
     * @param index Index of the task to be deleted in allTasks.
     * @return The chatbot update message to indicate successfully deleting the task.
     */
    public static String delete(int index) {
        Task task = (Task) allTasks.get(index);
        allTasks.remove(index);
        return ("Noted. I've removed this task:\n" + task.toString() + "\n" + getTaskCount());
    }

    /**
     * Sets the priority of a task.
     * @param index The index of the task to be prioritised.
     * @param priority The priority to be given to the specified task.
     * @return The chatbot update message to indicate successfully prioritising the task.
     */
    public static String prioritise(int index, String priority) {
        Task task = (Task) allTasks.get(index);
        return (task.setPriority(priority));
    }
}
