package friday.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import friday.fridayexceptions.FridayException;

/**
 * The Storage class helps load and save the file.
 */
public class Storage {
    private static String filepath;

    /**
     * Loads tasks from the file and saving tasks in the file
     * @param filepath The filepath to be loaded.
     */
    public Storage(String filepath) {
        Storage.filepath = filepath;
    }

    /**
     * Locates the TaskList file, creating it if it does not exist.
     * @return The tasks within the TaskList file.
     * @throws FridayException When a TaskList file cannot be created.
     */
    public ArrayList<String> loadFile() throws FridayException {
        try {
            File f = new File(filepath);
            if (f.createNewFile()) {
                throw new FridayException("TaskList not found, creating new TaskList file");
            }
            ArrayList<String> temporaryFile = new ArrayList<>();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                temporaryFile.add(s.nextLine());
            }
            return temporaryFile;
        } catch (IOException e) {
            throw new FridayException("an error occured");
        }
    }

    /**
     * Saves all tasks into the TaskList file.
     * @param allTasks The tasks to be saved into the TaskList file.
     * @throws IOException When the tasks cannot be written into the TaskList file.
     */
    public static void saveFile(ArrayList<String> allTasks) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (String allTask : allTasks) {
            fw.write(allTask + "\n");
        }
        fw.close();
    }
}
