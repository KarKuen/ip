package friday.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    TodoTask test2 = new TodoTask("test");

    @Test
    public void todoTaskTest() {
        assertEquals("[T][ ] test", test2.toString());
    }

}
