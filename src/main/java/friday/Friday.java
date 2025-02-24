package friday;

import friday.command.Command;
import friday.fridayexceptions.FridayException;
import friday.parser.Parser;
import friday.storage.Storage;
import friday.tasklist.TaskList;
import friday.ui.Ui;

/**
 * The Friday class sets up the chatbot's Ui, Storage, TaskList, and Parser.
 */
public class Friday {
    // relative filepath
    private static String filePath = "tasklist.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Initialises a newly created Friday object.
     */
    public Friday() {
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
        String reply;
        try {
            Command c = Parser.parse(input);
            reply = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (FridayException e) {
            ui.showError(e.getMessage());
            reply = e.getMessage();
        }
        return "Friday: \n" + reply;
    }
}
