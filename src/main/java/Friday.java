import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Friday {
    static ArrayList allTasks = new ArrayList<Task>(); //list of text inputs

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        introduction(); //Friday's greetings

        while (true) {
            Task task = new Task(in.nextLine()); //new task
            String action = task.description.split(" ")[0];

            if (action.compareTo("bye") == 0) {
                goodbye(); //goodbye message
                break;
            } else if (action.compareTo("list") == 0) {
                returnList();
            } else if (action.compareTo("unmark") == 0) {
                int index = Integer.parseInt(task.description.split(" ")[1]) -1;
                unmark(index);
            } else if (action.compareTo("mark") == 0) {
                int index = Integer.parseInt(task.description.split(" ")[1]) -1;
                mark(index);
            } else {
               addToList(task);
            }
        }
        out.close();
    }
    static void introduction() {
        System.out.println(
                "____________________________________\n" + "Hello ! I'm Friday\n" + "What can I do for you?\n" +
                        "____________________________________\n"
        );
    }
    static void goodbye() {
        System.out.println("____________________________________\n" + "Bye. Hope to see you again soon!" +
                                    "\n____________________________________\n"
        );
    }
    static void returnList() {
        int counter = 1;
        System.out.println("____________________________________\n" + "Here are the tasks in your list:");
        for (int i=0; i<allTasks.size(); i++) {
            System.out.println(counter + "." + allTasks.get(i).toString());
            counter++;
        }
        System.out.println("____________________________________\n");
    }
    static void addToList(Task task) {
        System.out.println("____________________________________\n" + "added: " + task.description +
                                    "\n____________________________________\n");
        allTasks.add(task);
    }
    static void unmark(int index) {
        Task task = (Task) allTasks.get(index);
        task.isDone = false;
        System.out.println("\n____________________________________\n");
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
        System.out.println("\n____________________________________\n");
    }
    static void mark(int index) {
        Task task = (Task) allTasks.get(index);
        task.isDone = true;
        System.out.println("\n____________________________________\n");
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
        System.out.println("\n____________________________________\n");
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
}
