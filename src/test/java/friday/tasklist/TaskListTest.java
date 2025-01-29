package friday.tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList test1 = new TaskList();
    String test1count = test1.getTaskCount();

    @Test
    public void taskCountTest() {
        assertEquals("Now you have 0 friday.tasks in the list." , test1count);
    }
}
