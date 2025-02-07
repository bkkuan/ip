package bryan.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Tasks {

    /**
     * Constructs a todo task with the given description.
     *
     * @param information the task description
     */
    public Todo(final String information) {
        super(information);
    }

    /**
     * Converts this todo into a file-friendly string format.
     *
     * @return a formatted string representing the todo
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + information;
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a formatted string with todo details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
