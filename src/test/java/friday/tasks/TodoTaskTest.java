package friday.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    private TodoTask test2 = new TodoTask("test");

    @Test
    public void todoTaskTest() {
        assertEquals("[T][ ] test", test2.toString());
    }

}
