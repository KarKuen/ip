package friday.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import friday.dukeexceptions.DukeException;

public class Storage {
    private static String filepath;

    //loading friday.tasks from the file and saving friday.tasks in the file
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    /**
     * Creates new TaskList file if it does not exist.
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
     * Adds every String in allTasks into the TaskList file.
     * @throws IOException If TaskList file is not found.
     */
    public static void saveFile(ArrayList allTasks) throws IOException{
        FileWriter fw = new FileWriter(filepath);
        for (int i = 0; i < allTasks.size(); i++) {
            fw.write(allTasks.get(i).toString() + "\n");
        }
        fw.close();
    }
}