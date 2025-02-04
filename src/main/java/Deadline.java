import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Tasks {
    protected LocalDate by;

    public Deadline(String information, LocalDate by) {
        super(information);
        this.by = by;
    }

    public Deadline(String information, String dateString) throws DateTimeParseException {
        super(information);
        this.by = LocalDate.parse(dateString);
    }


    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + information + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
