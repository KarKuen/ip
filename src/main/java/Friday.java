import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.util.Scanner;

import dukeexceptions.DukeException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TodoTask;
import Ui.Ui;
import storage.Storage;
import taskList.TaskList;
import praser.Praser;

public class Friday {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    //relative filepath
    private static String home = System.getProperty("user.home");
    private static String filePath = String.valueOf(java.nio.file.Paths.get(home, "Desktop", "taskList"));

    public Friday(String filepath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //I/O readers
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        Ui.greet();

        while (true) {
            String input = in.nextLine(); //new task
            String action = input.split(" ")[0];

            //continuously receive user input until user types "bye"
            if (action.compareTo("bye") == 0) {
                try {
                    //save allTasks into TaskList file
                    Storage.saveFile(TaskList.returnList());
                    Ui.farewell();
                } catch (IOException e){
                    //unable to save allTasks into TaskList file
                    System.out.println("Error saving TaskList");
                } finally {
                    break;
                }
            }

            //check input for valid actions
            try {
                Praser.checkInput(input);
            } catch(DukeException e) {
                System.out.println(Ui.formatString("Unknown action. Please input a Todo/Deadline/Event action"));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(Ui.formatString("Please provide more details for the "
                        + input.split(" ")[0] + " action."));
                continue;
            }

            if (action.compareTo("list") == 0) {
                Ui.printList(TaskList.returnList());
            } else if (action.compareTo("unmark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                TaskList.unmark(index);
            } else if (action.compareTo("mark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                TaskList.mark(index);
            } else if (action.compareTo("todo") == 0) {
                String[] texts = input.split(" ", 2);
                TaskList.addToList(new TodoTask(texts[1]));
            } else if (action.compareTo("deadline") == 0) {
                //separated input into 1)action and 2)description+/by+date+time
                String[] texts = input.split(" ", 2);
                //separates text into 1)description and 2)date+time
                String[] dates = texts[1].split("/by ", 2);

                try {
                    //checks if the user input date follows a valid format, and add it into allTasks if it is valid
                    LocalDateTime date = DeadlineTask.createDateFormatted(dates[1]);
                    TaskList.addToList(new DeadlineTask(dates[0], date));
                } catch (DukeException e) {
                    //if the user input date has an invalid format, do not add it into allTasks
                    System.out.println("please input a valid date");
                    continue;
                }
            } else if (action.compareTo("event") == 0) {
                String[] texts = input.split(" ", 2);
                String[] activity = texts[1].split("/from", 2);
                String[] period = activity[1].split("/to", 2);
                TaskList.addToList(new EventTask(activity[0], period[0], period[1]));
            } else if (action.compareTo("delete") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                TaskList.delete(index);
            }
        }
        out.close();
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Friday(filePath).run();
    }
}
