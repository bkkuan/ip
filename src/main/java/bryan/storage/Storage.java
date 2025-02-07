package bryan.storage;

import bryan.tasks.Deadline;
import bryan.tasks.Event;
import bryan.tasks.Tasks;
import bryan.tasks.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Storage class handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage instance for the given file name.
     *
     * @param fileName the path to the file where tasks are stored
     */
    public Storage(final String fileName) {
        this.filePath = Paths.get(fileName);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return an {@code ArrayList} of tasks loaded from the file
     */
    public ArrayList<Tasks> load() {
        final ArrayList<Tasks> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                return tasks;
            }
            for (final String line : Files.readAllLines(filePath)) {
                final String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue; // Minimal parts required
                }
                final boolean isDone = parts[1].equals("1");
                Tasks task = null;
                switch (parts[0]) {
                    case "T":
                        if (parts.length >= 3) {
                            task = new Todo(parts[2]);
                        }
                        break;
                    case "D":
                        if (parts.length >= 4) {
                            task = createDeadlineFromParts(parts);
                        }
                        break;
                    case "E":
                        if (parts.length >= 5) {
                            task = new Event(parts[2], parts[3], parts[4]);
                        }
                        break;
                    default:
                        break;
                }
                if (task != null) {
                    if (isDone) {
                        task.taskDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (final IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }

    /**
     * Creates a {@code Deadline} task from parts of a line read from the file.
     *
     * @param parts the string parts split by " | "
     * @return a {@code Deadline} task
     */
    private Deadline createDeadlineFromParts(final String[] parts) {
        final String dateString = parts[3];
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (final DateTimeParseException e1) {
            try {
                final DateTimeFormatter oldFormat =
                        DateTimeFormatter.ofPattern("MMMM d['th']['st']['nd']['rd']");
                date = LocalDate.parse(dateString, oldFormat);
            } catch (final DateTimeParseException e2) {
                System.out.println("Invalid date format: " + dateString + ". Using today's date.");
                date = LocalDate.now();
            }
        }
        return new Deadline(parts[2], date);
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the list of tasks to save
     */
    public void save(final ArrayList<Tasks> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            final ArrayList<String> lines = new ArrayList<>();
            for (final Tasks task : tasks) {
                lines.add(task.toFileFormat());
            }
            Files.write(filePath, lines);
        } catch (final IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
