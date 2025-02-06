package bryan.tasks;

public class Tasks {
    protected String information;
    protected boolean isDone;

    public Tasks(String information) {
        this.information = information;
        this.isDone = false;
    }
    //tasks marked done
    public void taskDone() {
        this.isDone = true;
    }
    //tasks unmarked or not done
    public void taskNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String toFileFormat() {
        throw new UnsupportedOperationException("toFileFormat not implemented");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + information;
    }
}
