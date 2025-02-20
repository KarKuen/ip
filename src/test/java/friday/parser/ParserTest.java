package friday.parser;
import  friday.command.Command;
import  friday.command.AddCommand;

import friday.fridayexceptions.FridayException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseTest() throws FridayException {
        Exception exception;
        exception = assertThrows(FridayException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("please input a valid action", exception.getMessage(), "Expected error message");

        Command command;
        command = Parser.parse("todo buy book");
        assertTrue(command instanceof AddCommand, "Expected AddCommand");

        command = Parser.parse("deadline return book /by 2025-2-20 1600");
        assertTrue(command instanceof AddCommand, "Expected AddCommand");

        command = Parser.parse("event book fair /from today /to tomorrow");
        assertTrue(command instanceof AddCommand, "Expected AddCommand");
    }
}
