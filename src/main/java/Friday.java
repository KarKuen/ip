import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dukeexceptions.DukeException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

public class Friday {
    //relative filepath
    static String home = System.getProperty("user.home");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(home, "Desktop", "TaskList");

    //TaskList that stores all tasks
    static ArrayList allTasks = new ArrayList<Task>();

    //list of allowable text inputs
    static List<String> availableActions = Arrays.asList("list", "mark", "unmark", "bye", "todo", "deadline", "event");
    static List<String> actionsWithDescription = Arrays.asList("mark", "unmark", "todo", "deadline", "event");

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        //I/O readers
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        greet();
        //creates TaskList file at the FILE_PATH if it doesn't exist
        retriveFile();

        while (true) {
            String input = in.nextLine(); //new task
            String action = input.split(" ")[0];

            //continuously receive user input until user types "bye"
            if (action.compareTo("bye") == 0) {
                try {
                    //save allTasks into TaskList file
                    farewell();
                } catch (IOException e){
                    //unable to save allTasks into TaskList file
                    System.out.println("Error saving TaskList");
                } finally {
                    break;
                }
            }

            //check input for valid actions
            try {
                checkInput(input);
            } catch(DukeException e) {
                System.out.println(returnMessage("Unknown action. Please input a Todo/Deadline/Event action"));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(returnMessage("Please provide more details for the "
                        + input.split(" ")[0] + " action."));
                continue;
            }

            if (action.compareTo("list") == 0) {
                returnList();
            } else if (action.compareTo("unmark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                unmark(index);
            } else if (action.compareTo("mark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                mark(index);
            } else if (action.compareTo("todo") == 0) {
                String[] texts = input.split(" ", 2);
                addToList(new TodoTask(texts[1]));
            } else if (action.compareTo("deadline") == 0) {
                //separated input into 1)action and 2)description+/by+date+time
                String[] texts = input.split(" ", 2);
<<<<<<< HEAD
                String[] dates = texts[1].split("/by", 2);
                addToList(new DeadlineTask(dates[0], dates[1]));
=======
                //separates text into 1)description and 2)date+time
                String[] dates = texts[1].split("/by ", 2);

                try {
                    //checks if the user input date follows a valid format, and add it into allTasks if it is valid
                    LocalDateTime date = DeadlineTask.createDateFormatted(dates[1]);
                    addToList(new DeadlineTask(dates[0], date));
                } catch (DukeException e) {
                    //if the user input date has an invalid format, do not add it into allTasks
                    System.out.println("please input a valid date");
                    continue;
                }
>>>>>>> branch-Level-8
            } else if (action.compareTo("event") == 0) {
                String[] texts = input.split(" ", 2);
                String[] activity = texts[1].split("/from", 2);
                String[] period = activity[1].split("/to", 2);
                addToList(new EventTask(activity[0], period[0], period[1]));
            } else if (action.compareTo("delete") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                delete(index);
            }
        }
        out.close();
    }

    /**
     * Returns a String with padding above and below.
     * @param message String to be padded.
     * @return Padded String.
     */
    public static String returnMessage(String message) {
        return ("____________________________________\n" + message + "\n____________________________________\n");
    }

    /**
     * Returns a greeting.
     */
    public static void greet() {
        System.out.print(returnMessage("Hello ! I'm Friday\n" + "What can I do for you?"));
    }

    /**
     * Adds every String in allTasks into the TaskList file.
     * @throws IOException If TaskList file is not found.
     */
    public static void farewell() throws IOException{
        System.out.println(returnMessage("Bye. Hope to see you again soon!"));
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        for (int i = 0; i < allTasks.size(); i++) {
            fw.write(allTasks.get(i).toString() + "\n");
        }
        fw.close();
    }

    /**
     * Prints out every item in allTasks with paddings above and below.
     */
    public static void returnList() {
        int counter = 1;
        System.out.println("____________________________________\n" + "Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(counter + "." + allTasks.get(i).toString());
            counter++;
        }
        System.out.println("____________________________________");
    }

    /**
     * Adds a task into allTasks.
     * @param task Task to be added into allTasks.
     */
    public static void addToList(Task task) {
        allTasks.add(task);
        System.out.print(returnMessage("Got it. I've added this task:\n" + task.toString() + "\n" + getTaskCount()));
    }

    /**
     * Returns a String indicating the number of tasks in allTasks.
     * @return String with the number of tasks in allTasks.
     */
    public static String getTaskCount() {
        return ("Now you have " + allTasks.size() + " tasks in the list.");
    }

    /**
     * Unmarks the checkbox of a task in allTasks.
     * @param index Index of the checkbox index to be unmarked.
     */
    public static void unmark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(false);
        System.out.print(returnMessage("OK, I've marked this task as not done yet:\n" + task.toString()));
    }

    /**
     * Marks the checkbox of a task in allTasks.
     * @param index Index of the checkbox index to be marked.
     */
    public static void mark(int index) {
        Task task = (Task) allTasks.get(index);
        task.setTaskStatus(true);
        System.out.print(returnMessage("Nice! I've marked this task as done:\n" + task.toString()));
    }

    /**
     * Deletes a task in allTasks.
     * @param index Index of the task to be deleted in allTasks.
     */
    public static void delete(int index) {
        Task task = (Task) allTasks.get(index);
        allTasks.remove(index);
        System.out.print(returnMessage("Noted. I've removed this task:\n" + task.toString() + "\n" + getTaskCount()));
    }

    /**
     * Creates new TaskList file if it does not exist.
     */
    public static void retriveFile() {
        try {
            File f = new File(String.valueOf(filePath));
            if (f.createNewFile()) {
                System.out.print(returnMessage("creating new TaskList file"));
            } else {
                System.out.print(returnMessage("accessing TaskList file"));
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    allTasks.add(s.nextLine());
                }
            }
        } catch (IOException e) {
            System.out.println("an error occured");
        }
    }

    /**
     * Checks if the user input is a valid action within availableActions and actionsWithDescription.
     * @param input The user input.
     * @throws DukeException If the user input is not an action within availableActions.
     */
    public static void checkInput(String input) throws DukeException {
        if(input.split(" ").length <= 1) {
            String action = input.split(" ")[0];
            if(!availableActions.contains(action)) {
                throw new DukeException("please input a valid action");
            } else {
                //check if there exist a description
                if ((input.split(" ").length <= 1) &&
                        //check if the action requires a description
                        (actionsWithDescription.contains(input.split(" ")[0]))) {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }

        }
    }
}
