package storage;

import Ui.Ui;
import dukeexceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filepath;

    //loading tasks from the file and saving tasks in the file
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
                throw new DukeException(Ui.formatString("TaskList not found, creating new TaskList file"));
            } else {
                ArrayList temporaryFile = new ArrayList<>();
                System.out.print(Ui.formatString("accessing TaskList file"));
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