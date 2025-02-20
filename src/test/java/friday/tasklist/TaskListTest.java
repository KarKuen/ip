package friday.tasklist;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskListTest() {
        TaskList taskList1 = new TaskList();
        assertTrue(TaskList.returnList().isEmpty(), "New TaskList should be empty.");

        ArrayList<String> savedTasks = new ArrayList<>();
        savedTasks.add("[T][ ] buy book\n");
        savedTasks.add("[D][ ] return book  (by: Feb 20 2025 10pm)\n");
        savedTasks.add("[E][ ] book fair  (from: 10  to:  12)\n");

        TaskList taskList2 = new TaskList(savedTasks);
        ArrayList<String> tasks = TaskList.returnList();

        assertEquals(3, tasks.size(), "TaskList should have 3 tasks.");

        assertTrue(tasks.get(0).contains("[T][ ] buy book"), "TodoTask should remain unchanged");
        assertTrue(tasks.get(1).contains("[D][ ] return book  (by: Feb 20 2025 10pm)"),
                                "DeadLineTask remain unchanged");
        assertTrue(tasks.get(2).contains("[E][ ] book fair  (from: 10  to:  12)"),
                                "EventTask should remain unchanged");
    }
}
