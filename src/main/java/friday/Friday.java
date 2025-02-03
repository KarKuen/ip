package friday;

import friday.command.Command;
import friday.dukeexceptions.DukeException;
import friday.ui.Ui;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.parser.Parser;

import java.io.FileNotFoundException;
import java.util.Scanner;

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

        Scanner in = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(in);
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Friday(filePath).run();
    }
}
