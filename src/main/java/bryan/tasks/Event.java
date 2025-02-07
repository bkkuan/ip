package bryan.tasks;

/**
 * Represents an event task.
 */
public class Event extends Tasks {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the given description, start, and end times.
     *
     * @param information the event description
     * @param from        the start time/date
     * @param to          the end time/date
     */
    public Event(String information, String from, String to) {
        super(information);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts this event into a file-friendly string format.
     *
     * @return a formatted string representing the event
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + information + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a formatted string with event details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
