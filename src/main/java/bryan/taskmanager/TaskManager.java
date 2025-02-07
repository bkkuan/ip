package bryan.taskmanager;

import bryan.exception.BryanException;
import bryan.tasks.Deadline;
import bryan.tasks.Event;
import bryan.tasks.Tasks;
import bryan.tasks.Todo;

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
     * @param tasks the initial list of tasks
     */
    public TaskManager(final ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return an {@code ArrayList} of tasks
     */
    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the input string.
     *
     * @param input the input specifying the task details
     * @throws BryanException if the task description is invalid
     */
    public void addTask(final String input) throws BryanException {
        final Tasks task = createTask(input);
        tasks.add(task);
        printAddConfirmation(task);
    }

    /**
     * Creates a task from the input string.
     *
     * @param input the input string
     * @return a {@code Tasks} object representing the new task
     * @throws BryanException if the input does not specify a valid task
     */
    private Tasks createTask(final String input) throws BryanException {
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
     * @param input the input string
     * @return a {@code Todo} task
     * @throws BryanException if the description is empty
     */
    private Todo createTodo(final String input) throws BryanException {
        final String description = input.substring(5).trim();
        validateDescription(description);
        return new Todo(description);
    }

    /**
     * Creates a {@code Deadline} task.
     *
     * @param input the input string in the format "deadline description /by date"
     * @return a {@code Deadline} task
     * @throws BryanException if the input format is invalid or the date is wrong
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
     * @param input the input string in the format "event description /from start /to end"
     * @return an {@code Event} task
     * @throws BryanException if the input format is invalid
     */
    private Event createEvent(final String input) throws BryanException {
        final String[] parts = input.substring(6).split("/from|/to");
        if (parts.length < 3) {
            throw new BryanException("Invalid event format");
        }
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Prints all tasks to the console.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty. Let's get started!");
            return;
        }
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index the index of the task to mark as done
     */
    public void markTask(final int index) {
        validateIndex(index);
        tasks.get(index).taskDone();
        System.out.printf("Marked task %d as done:\n  %s\n", index + 1, tasks.get(index));
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index the index of the task to unmark
     */
    public void unmarkTask(final int index) {
        validateIndex(index);
        tasks.get(index).taskNotDone();
        System.out.printf("Marked task %d as not done:\n  %s\n", index + 1, tasks.get(index));
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(final int index) {
        validateIndex(index);
        final Tasks removed = tasks.remove(index);
        System.out.printf("Removed task %d:\n  %s\nNow you have %d tasks\n",
                index + 1, removed, tasks.size());
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param description the task description
     * @throws BryanException if the description is empty
     */
    private void validateDescription(final String description) throws BryanException {
        if (description.isEmpty()) {
            throw new BryanException("Task description cannot be empty");
        }
    }

    /**
     * Validates that the given index is within the bounds of the task list.
     *
     * @param index the task index
     * @throws IllegalArgumentException if the index is invalid
     */
    private void validateIndex(final int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }
    }

    /**
     * Prints a confirmation message when a task is added.
     *
     * @param task the task that was added
     */
    private void printAddConfirmation(final Tasks task) {
        System.out.printf("Added task:\n  %s\nNow you have %d tasks\n", task, tasks.size());
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword the search keyword
     * @return an {@code ArrayList} of tasks whose descriptions contain the keyword
     */
    public ArrayList<Tasks> findTasks(final String keyword) {
        final ArrayList<Tasks> matchingTasks = new ArrayList<>();
        for (final Tasks task : tasks) {
            // Use toString() or a dedicated getter if available.
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
