package bryan.taskmanager;

import bryan.exception.BryanException;
import bryan.tasks.Deadline;
import bryan.tasks.Event;
import bryan.tasks.Tasks;
import bryan.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * TaskManager class manages the list of tasks and operations on them.
 */
public class TaskManager {
    private final ArrayList<Tasks> tasks;

    /**
     * Constructs a TaskManager with an empty list of tasks.
     */
    public TaskManager() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a TaskManager with the provided list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskManager(final ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return an {@code ArrayList} of tasks.
     */
    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the input string.
     *
     * @param input the input specifying the task details.
     * @return a confirmation message after adding the task.
     * @throws BryanException if the task description is invalid.
     */
    public String addTask(final String input) throws BryanException {
        assert tasks != null : "Task cannot be null";
        final Tasks task = createTask(input);
        tasks.add(task);
        return printAddConfirmation(task);
    }

    /**
     * Formats a status change message for a task.
     *
     * @param index  the zero-based index of the task in the task list
     * @param task   the task whose status was updated
     * @param marked {@code true} if the task is marked as done, {@code false} if unmarked
     * @return a formatted string message indicating the updated status of the task
     */
    private String formatStatusChangeMessage(final int index, final Tasks task, final boolean marked) {
        String status = marked ? "done" : "not done";
        return "Marked task " + (index + 1) + " as " + status + ":\n  " + task.toString();
    }

    /**
     * Creates a task from the input string.
     *
     * @param input the input string.
     * @return a {@code Tasks} object representing the new task.
     * @throws BryanException if the input does not specify a valid task.
     */
    private Tasks createTask(final String input) throws BryanException {
        assert input != null : "Input to createTask() should not be null";
        if (input.startsWith("todo ")) {
            return createTodo(input);
        }
        if (input.startsWith("deadline ")) {
            return createDeadline(input);
        }
        if (input.startsWith("event ")) {
            return createEvent(input);
        }
        throw new BryanException("Empty description. Please specify a task");
    }

    /**
     * Creates a {@code Todo} task.
     *
     * @param input the input string.
     * @return a {@code Todo} task.
     * @throws BryanException if the description is empty.
     */
    private Todo createTodo(final String input) throws BryanException {
        final String description = input.substring(5).trim();
        validateDescription(description);
        return new Todo(description);
    }

    /**
     * Creates a {@code Deadline} task.
     *
     * @param input the input string in the format "deadline description /by date".
     * @return a {@code Deadline} task.
     * @throws BryanException if the input format is invalid or the date is wrong.
     */
    private Deadline createDeadline(final String input) throws BryanException {
        final String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new BryanException("Invalid deadline format");
        }
        final String description = parts[0].trim();
        final String dateString = parts[1].trim();
        try {
            return new Deadline(description, dateString);
        } catch (final DateTimeParseException e) {
            throw new BryanException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    /**
     * Creates an {@code Event} task.
     *
     * @param input the input string in the format "event description /from start /to end".
     * @return an {@code Event} task.
     * @throws BryanException if the input format is invalid.
     */
    private Event createEvent(final String input) throws BryanException {
        final String[] parts = input.substring(6).split("/from|/to");
        if (parts.length < 3) {
            throw new BryanException("Invalid event format");
        }
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Returns a confirmation message when a task is added.
     *
     * @param task the task that was added.
     * @return a confirmation message.
     */
    private String printAddConfirmation(final Tasks task) {
        return "Added task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " tasks.";
    }

    /**
     * Returns a string listing all tasks.
     *
     * @return a string representing all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty. Let's get started!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index the index of the task to mark as done.
     * @return a confirmation message.
     */
    public String markTask(final int index) {
        validateIndex(index);
        tasks.get(index).taskDone();
        return formatStatusChangeMessage(index, tasks.get(index), true);
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index the index of the task to unmark.
     * @return a confirmation message.
     */
    public String unmarkTask(final int index) {
        validateIndex(index);
        tasks.get(index).taskNotDone();
        return formatStatusChangeMessage(index, tasks.get(index), false);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete.
     * @return a confirmation message.
     */
    public String deleteTask(final int index) {
        validateIndex(index);
        final Tasks removed = tasks.remove(index);
        return "Removed task " + (index + 1) + ":\n  " + removed.toString() + "\nNow you have " + tasks.size() + " tasks.";
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param description the task description.
     * @throws BryanException if the description is empty.
     */
    private void validateDescription(final String description) throws BryanException {
        if (description.isEmpty()) {
            throw new BryanException("Task description cannot be empty");
        }
    }

    /**
     * Validates that the given index is within the bounds of the task list.
     *
     * @param index the task index.
     * @throws IllegalArgumentException if the index is invalid.
     */
    private void validateIndex(final int index) {
        // Assert that the index is within the bounds of the tasks list.
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }
    }

    /**
     * Snoozes a deadline task by updating its due date.
     *
     * @param index the index of the task to snooze.
     * @param newDateString the new due date in yyyy-mm-dd format.
     * @return a confirmation message.
     * @throws BryanException if the task is not a deadline or the date format is invalid.
     */
    public String snoozeTask(final int index, final String newDateString) throws BryanException {
        validateIndex(index);
        Tasks task = tasks.get(index);
        if (!(task instanceof bryan.tasks.Deadline)) {
            throw new BryanException("Only deadline tasks can be snoozed.");
        }
        try {
            LocalDate newDate = LocalDate.parse(newDateString);
            ((bryan.tasks.Deadline) task).setBy(newDate);
            return "Snoozed task " + (index + 1) + " to new due date: " + newDate.toString();
        } catch (DateTimeParseException e) {
            throw new BryanException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword the search keyword.
     * @return an {@code ArrayList} of tasks whose descriptions contain the keyword.
     */
    public ArrayList<Tasks> findTasks(final String keyword) {
        final ArrayList<Tasks> matchingTasks = new ArrayList<>();
        for (final Tasks task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
