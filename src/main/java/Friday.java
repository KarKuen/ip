import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

import command.Command;
import dukeexceptions.DukeException;
import ui.Ui;
import storage.Storage;
import taskList.TaskList;
import parser.Parser;

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
            ui.showLoadingError("A problem occured when loading in TaskList file");
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

            /*
            //check input for valid actions
            try {
                Parser.parse(input);
            } catch (DukeException e) {
                System.out.println(Ui.formatString("Unknown action. Please input a Todo/Deadline/Event action"));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Ui.formatString("Please provide more details for the "
                        + input.split(" ")[0] + " action."));
                continue;
            }
            */

        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Friday(filePath).run();
    }
}
