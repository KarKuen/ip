package tasks;

<<<<<<< HEAD
//a deadline task with a deadline, indicated by the "by" variable.
public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
=======
import dukeexceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

//a deadline task with a deadline, indicated by the "by" variable.
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
>>>>>>> branch-Level-8
        super(description);
        this.by = by;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by:" + by + ")");
=======
    /**
     *
     * @param date The date to check against.
     * @return Return the date as a LocalDateTime if it is in a valid format.
     * @throws DukeException If the date has an invalid format.
     */
    public static LocalDateTime createDateFormatted(String date) throws DukeException {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"))
                .toFormatter();

        try {
            LocalDateTime checkDate = LocalDateTime.parse(date, dateFormatter);
            return checkDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format");
        }
    }

    /**
     * Returns a formatted date.
     * @return The deadline date following the pattern of MMM dd yyyy ha.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha")) + ")");
>>>>>>> branch-Level-8
    }
}
