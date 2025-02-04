public class Deadline extends Tasks {
    protected String by;
    public Deadline(String information, String by) {
        super(information);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + information + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
