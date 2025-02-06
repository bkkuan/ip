package bryan.taskmanager;

import bryan.exception.BryanException;
import bryan.tasks.Deadline;
import bryan.tasks.Event;
import bryan.tasks.Tasks;
import bryan.tasks.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Tasks> tasks;

    public TaskManager() {
        this(new ArrayList<>());
    }

    public TaskManager(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    public void addTask(String input) throws BryanException {
        Tasks task = createTask(input);
        tasks.add(task);
        printAddConfirmation(task);
    }

    private Tasks createTask(String input) throws BryanException {
        if (input.startsWith("todo ")) return createTodo(input);
        if (input.startsWith("deadline ")) return createDeadline(input);
        if (input.startsWith("event ")) return createEvent(input);
        throw new BryanException("Empty description. Please specify a task");
    }

    private Todo createTodo(String input) throws BryanException {
        String description = input.substring(5).trim();
        validateDescription(description);
        return new Todo(description);
    }

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

    private Event createEvent(String input) throws BryanException {
        String[] parts = input.substring(6).split("/from|/to");
        if (parts.length < 3) throw new BryanException("Invalid event format");
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

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

    public void markTask(int index) {
        validateIndex(index);
        tasks.get(index).taskDone();
        System.out.printf("Marked task %d as done:\n  %s\n", index + 1, tasks.get(index));
    }

    public void unmarkTask(int index) {
        validateIndex(index);
        tasks.get(index).taskNotDone();
        System.out.printf("Marked task %d as not done:\n  %s\n", index + 1, tasks.get(index));
    }

    public void deleteTask(int index) {
        validateIndex(index);
        Tasks removed = tasks.remove(index);
        System.out.printf("Removed task %d:\n  %s\nNow you have %d tasks\n",
                index + 1, removed, tasks.size());
    }

    private void validateDescription(String description) throws BryanException {
        if (description.isEmpty()) {
            throw new BryanException("Task description cannot be empty");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }
    }

    private void printAddConfirmation(Tasks task) {
        System.out.printf("Added task:\n  %s\nNow you have %d tasks\n",
                task, tasks.size());
    }
}
