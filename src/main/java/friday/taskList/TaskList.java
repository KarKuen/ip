package friday.taskList;

import friday.ui.Ui;
import friday.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    //TaskList that stores all friday.tasks
    private static ArrayList allTasks;

    public TaskList(ArrayList temporaryFile) {
        this.allTasks = temporaryFile;
    }

    public TaskList() {
        this.allTasks = new ArrayList();
    }

    public static ArrayList returnList() {
        return allTasks;
    }

    /**
     * Adds a task into allTasks.
     * @param task Task to be added into allTasks.
     */
    public static void addToList(Task task) {
        allTasks.add(task);
        Ui.showUpdate("Got it. I've added this task:\n" + task.toString() + "\n" + getTaskCount());
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
     */
    public static void unmark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(false);
        Ui.showUpdate("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Marks the checkbox of a task in allTasks.
     * @param index Index of the checkbox index to be marked.
     */
    public static void mark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(true);
        Ui.showUpdate("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Deletes a task in allTasks.
     * @param index Index of the task to be deleted in allTasks.
     */
    public static void delete(int index) {
        Task task = (Task) allTasks.get(index);
        allTasks.remove(index);
        Ui.showUpdate("Noted. I've removed this task:\n" + task.toString() + "\n" + getTaskCount());
    }
}