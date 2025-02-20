package friday.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList test1 = new TaskList();
    private String test1count = test1.getTaskCount();

    @Test
    public void taskCountTest() {
        assertEquals("Now you have 0 friday.tasks in the list." , test1count);
    }
}
