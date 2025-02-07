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
    public TaskManager(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList of tasks
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
    public void addTask(String input) throws BryanException {
        Tasks task = createTask(input);
        tasks.add(task);
        printAddConfirmation(task);
    }

    /**
     * Creates a task from the input string.
     *
     * @param input the input string
     * @return a Tasks object representing the new task
     * @throws BryanException if the input does not specify a valid task
     */
    private Tasks createTask(String input) throws BryanException {
        if (input.startsWith("todo ")) return createTodo(input);
        if (input.startsWith("deadline ")) return createDeadline(input);
        if (input.startsWith("event ")) return createEvent(input);
        throw new BryanException("Empty description. Please specify a task");
    }

    /**
     * Creates a Todo task.
     *
     * @param input the input string
     * @return a Todo task
     * @throws BryanException if the description is empty
     */
    private Todo createTodo(String input) throws BryanException {
        String description = input.substring(5).trim();
        validateDescription(description);
        return new Todo(description);
    }

    /**
     * Creates a Deadline task.
     *
     * @param input the input string in the format "deadline description /by date"
     * @return a Deadline task
     * @throws BryanException if the input format is invalid or date is wrong
     */
    private Deadline createDeadline(String input) throws BryanException {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) throw new BryanException("Invalid deadline format");

        String description = parts[0].trim();
        String dateString = parts[1].trim();

        try {
            return new Deadline(description, dateString);
        } catch (DateTimeParseException e) {
            throw new BryanException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    /**
     * Creates an Event task.
     *
     * @param input the input string in the format "event description /from start /to end"
     * @return an Event task
     * @throws BryanException if the input format is invalid
     */
    private Event createEvent(String input) throws BryanException {
        String[] parts = input.substring(6).split("/from|/to");
        if (parts.length < 3) throw new BryanException("Invalid event format");
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
    public void markTask(int index) {
        validateIndex(index);
        tasks.get(index).taskDone();
        System.out.printf("Marked task %d as done:\n  %s\n", index + 1, tasks.get(index));
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index the index of the task to unmark
     */
    public void unmarkTask(int index) {
        validateIndex(index);
        tasks.get(index).taskNotDone();
        System.out.printf("Marked task %d as not done:\n  %s\n", index + 1, tasks.get(index));
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        validateIndex(index);
        Tasks removed = tasks.remove(index);
        System.out.printf("Removed task %d:\n  %s\nNow you have %d tasks\n",
                index + 1, removed, tasks.size());
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param description the task description
     * @throws BryanException if the description is empty
     */
    private void validateDescription(String description) throws BryanException {
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
    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }
    }

    /**
     * Prints a confirmation message when a task is added.
     *
     * @param task the task that was added
     */
    private void printAddConfirmation(Tasks task) {
        System.out.printf("Added task:\n  %s\nNow you have %d tasks\n",
                task, tasks.size());
    }
}
