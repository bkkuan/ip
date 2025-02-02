public class Event extends Tasks {
    protected String from;
    protected String to;

    public Event(String information, String from, String to) {
        super(information);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
