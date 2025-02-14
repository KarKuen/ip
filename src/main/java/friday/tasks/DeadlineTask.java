package friday.tasks;

import friday.fridayexceptions.FridayException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

//a deadline task with a deadline, indicated by the "by" variable.
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public static final String EVENTTYPE = String.valueOf(OPENBRACKET + "D" + CLOSEBRACKET);
    public static final String BYFORMATSTRING = " (by: ";

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a LocalDateTime object.
     * @param date The date to check against.
     * @return Return the date as a LocalDateTime if it is in a valid format.
     * @throws FridayException If the date has an invalid format.
     */
    public static LocalDateTime createDateFormatted(String date) throws FridayException {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMMM d HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy hha"))
                .toFormatter();

        try {
            LocalDateTime checkDate = LocalDateTime.parse(date, dateFormatter);
            return checkDate;
        } catch (DateTimeParseException e) {
            throw new FridayException("Invalid date format");
        }
    }

    public static String formatBy(LocalDateTime by) {
        return (by.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha")));
    }

    /**
     * Returns a formatted date.
     * @return The deadline date following the pattern of MMM dd yyyy ha.
     */
    @Override
    public String toString() {
        return (EVENTTYPE
                + super.toString()
                + BYFORMATSTRING
                + formatBy(by)
                + ENDINGBRACKET);
    }
}
