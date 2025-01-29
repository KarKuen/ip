package friday.storage;

import friday.dukeexceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filepath;

    /**
     * Loading tasks from the file and saving tasks in the file
     * @param filepath The filepath to be loaded.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Locates the TaskList file, creating it if it does not exist.
     * @return The tasks within the TaskList file.
     * @throws DukeException When a TaskList file cannot be created.
     */
    public ArrayList loadFile() throws DukeException {
        try {
            File f = new File(filepath);
            if (f.createNewFile()) {
                throw new DukeException("TaskList not found, creating new TaskList file");
            } else {
                ArrayList temporaryFile = new ArrayList<>();
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    temporaryFile.add(s.nextLine());
                }
                return temporaryFile;
            }
        } catch (IOException e) {
            throw new DukeException("an error occured");
        }
    }

    /**
     * Saves all tasks into the TaskList file.
     * @param allTasks The tasks to be saved into the TaskList file.
     * @throws IOException When the tasks cannot be written into the TaskList file.
     */
    public static void saveFile(ArrayList allTasks) throws IOException{
        FileWriter fw = new FileWriter(filepath);
        for (int i = 0; i < allTasks.size(); i++) {
            fw.write(allTasks.get(i).toString() + "\n");
        }
        fw.close();
    }
}