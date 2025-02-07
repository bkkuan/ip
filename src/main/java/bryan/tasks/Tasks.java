package bryan.tasks;

/**
 * Abstract class representing a generic task.
 */
public abstract class Tasks {
    protected String information;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param information the task description
     */
    public Tasks(String information) {
        this.information = information;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void taskNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task into a string representation suitable for file storage.
     *
     * @return a formatted string representing the task
     */
    public abstract String toFileFormat();

    /**
     * Returns the status icon of the task.
     *
     * @return "[X]" if done, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string containing the status icon and the description
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + information;
    }
}
