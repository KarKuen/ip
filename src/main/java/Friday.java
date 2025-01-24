import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Friday {
    static ArrayList allTasks = new ArrayList<Task>();
    //list of text inputs
    static List<String> availableActions =
            Arrays.asList("list", "mark", "unmark", "bye", "todo", "deadline", "event");
    static List<String> actionsWithDescription =
            Arrays.asList("mark", "unmark", "todo", "deadline", "event");


    public static void main(String[] args) throws DukeException {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        introduction(); //Friday's greetings

        while (true) {
            String input = in.nextLine(); //new task
            String action = input.split(" ")[0];

            if (action.compareTo("bye") == 0) {
                goodbye(); //goodbye message
                break;
            } try {
                checkInput(input);
            } catch(DukeException e) {
                System.out.println(returnMessage("Unknown action. Please input a Todo/Deadline/Event action"));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(returnMessage("Please provide more details for the " + input.split(" ")[0] + " action."));
                continue;
            }
            if (action.compareTo("list") == 0) {
                returnList();
            } else if (action.compareTo("unmark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) -1;
                unmark(index);
            } else if (action.compareTo("mark") == 0) {
                int index = Integer.parseInt(input.split(" ")[1]) -1;
                mark(index);
            } else if (action.compareTo("todo") == 0) {
                String[] texts = input.split(" ", 2);
                addToList(new Todo(texts[1]));
            } else if (action.compareTo("deadline") == 0) {
                String[] texts = input.split(" ", 2);
                String[] dates = texts[1].split("/by", 2);
                addToList(new Deadline(dates[0], dates[1]));
            } else if (action.compareTo("event") == 0) {
                String[] texts = input.split(" ", 2);
                String[] activity = texts[1].split("/from", 2);
                String[] period = activity[1].split("/to", 2);
                addToList(new Event(activity[0], period[0], period[1]));
            }
        }
        out.close();
    }
    static String returnMessage(String message) {
        return ("____________________________________\n" + message + "\n____________________________________\n");
    }

    static void introduction() {
        System.out.print(returnMessage("Hello ! I'm Friday\n" + "What can I do for you?"));
    }
    static void goodbye() {
        System.out.println(returnMessage("Bye. Hope to see you again soon!"));
    }
    static void returnList() {
        int counter = 1;
        System.out.println("____________________________________\n" + "Here are the tasks in your list:");
        for (int i=0; i<allTasks.size(); i++) {
            System.out.println(counter + "." + allTasks.get(i).toString());
            counter++;
        }
        System.out.println("____________________________________");
    }
    static void addToList(Task task) {
        allTasks.add(task);
        System.out.print(returnMessage("Got it. I've added this task:\n" + task.toString() + "\n" + taskcounter()));
    }
    static String taskcounter() {
        return ("Now you have " + allTasks.size() + " tasks in the list.");
    }
    static void unmark(int index) {
        Task task = (Task) allTasks.get(index);
        task.isDone = false;
        System.out.print(returnMessage("OK, I've marked this task as not done yet:\n" + task.toString()));
    }
    static void mark(int index) {
        Task task = (Task) allTasks.get(index);
        task.isDone = true;
        System.out.print(returnMessage("Nice! I've marked this task as done:\n" + task.toString()));
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

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return ("[T]" + super.toString());
        }
    }

    public static class Deadline extends Task {
        protected String by;
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return ("[D]" + super.toString() + " (by:" + by + ")");
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return ("[E]" + super.toString() + " (from:" + from + "to:" + to + ")");
        }
    }

    public static class DukeException extends Exception {
        public DukeException() {
            super();
        }
    }

    public static void checkInput(String input) throws DukeException {
        if(input.split(" ").length <= 1) {
            String action = input.split(" ")[0];
            if(!availableActions.contains(action)) {
                throw new DukeException();
            } else {
                //check if there exist a description
                if ((input.split(" ").length <= 1) &&
                        //check if the action requires a description
                        (actionsWithDescription.contains(input.split(" ")[0]))) {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }

        }
    }
}
