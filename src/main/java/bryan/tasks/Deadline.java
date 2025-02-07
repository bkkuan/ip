package bryan.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Tasks {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param information the task description
     * @param by          the due date
     */
    public Deadline(String information, LocalDate by) {
        super(information);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description and due date string.
     *
     * @param information the task description
     * @param dateString  the due date in string format
     * @throws DateTimeParseException if the date string is invalid
     */
    public Deadline(String information, String dateString) throws DateTimeParseException {
        super(information);
        this.by = LocalDate.parse(dateString);
    }

    /**
     * Converts this deadline into a file-friendly string format.
     *
     * @return a formatted string representing the deadline
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + information + " | " + by;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a formatted string with task details and due date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

