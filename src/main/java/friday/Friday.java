package friday;

import friday.command.Command;
import friday.fridayexceptions.FridayException;
import friday.ui.Ui;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.parser.Parser;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friday {
    //relative filepath
    private static String home = System.getProperty("user.home");
    private static String filePath = String.valueOf(java.nio.file.Paths.get(home, "Desktop", "taskList"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Friday(String filepath) {
        boolean isExit = false;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FridayException e) {
            ui.showLoadingError("A problem occured when loading in TaskList file");
            tasks = new TaskList();
        }
    }

    public Friday() {
        boolean isExit = false;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FridayException e) {
            ui.showLoadingError("A problem occured when loading in TaskList file");
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        String reply = "default message";
        try {
            Command c = Parser.parse(input);
            reply = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (FridayException e) {
            ui.showError(e.getMessage());
        }
        return "Friday: \n" + reply;
    }
}
