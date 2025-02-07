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

public class Storage {
    private final Path filePath;

    public Storage(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public ArrayList<Tasks> load() {
        ArrayList<Tasks> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) return tasks;

            for (String line : Files.readAllLines(filePath)) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue; // Fix: Changed from <4 to <3

                boolean isDone = parts[1].equals("1");
                Tasks task = null;
                switch (parts[0]) {
                    case "T":
                        if (parts.length >= 3) { // Explicit check for Todo
                            task = new Todo(parts[2]);
                        }
                        break;
                    case "D":
                        if (parts.length >= 4) { // Check for Deadline
                            task = createDeadlineFromParts(parts);
                        }
                        break;
                    case "E":
                        if (parts.length >= 5) { // Check for Event
                            task = new Event(parts[2], parts[3], parts[4]);
                        }
                        break;
                }

                if (task != null) {
                    if (isDone) task.taskDone();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }

    private Deadline createDeadlineFromParts(String[] parts) {
        String dateString = parts[3];
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("MMMM d['th']['st']['nd']['rd']");
                date = LocalDate.parse(dateString, oldFormat);
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid date format: " + dateString + ". Using today's date.");
                date = LocalDate.now();
            }
        }

        return new Deadline(parts[2], date);
    }

    public void save(ArrayList<Tasks> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            ArrayList<String> lines = new ArrayList<>();
            for (Tasks task : tasks) {
                lines.add(task.toFileFormat());
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
