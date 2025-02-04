import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Tasks> load() throws IOException {
        ArrayList<Tasks> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) return tasks;

        for (String line : Files.readAllLines(filePath)) {
            parseTask(line).ifPresent(tasks::add);
        }
        return tasks;
    }

    public void save(ArrayList<Tasks> tasks) throws IOException {
        createParentDirectory();
        Files.write(filePath, serializeTasks(tasks));
    }

    private void createParentDirectory() throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) Files.createDirectories(parent);
    }

    private List<String> serializeTasks(ArrayList<Tasks> tasks) {
        List<String> lines = new ArrayList<>();
        for (Tasks task : tasks) {
            lines.add(task.toFileFormat());
        }
        return lines;
    }

    private java.util.Optional<Tasks> parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");

            switch (parts[0]) {
                case "T":
                    return java.util.Optional.of(parseTodo(parts, isDone));
                case "D":
                    return java.util.Optional.of(parseDeadline(parts, isDone));
                case "E":
                    return java.util.Optional.of(parseEvent(parts, isDone));
                default:
                    return java.util.Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return java.util.Optional.empty();
        }
    }

    private Todo parseTodo(String[] parts, boolean isDone) {
        Todo todo = new Todo(parts[2]);
        if (isDone) todo.taskDone();
        return todo;
    }

    private Deadline parseDeadline(String[] parts, boolean isDone) {
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (isDone) deadline.taskDone();
        return deadline;
    }

    private Event parseEvent(String[] parts, boolean isDone) {
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (isDone) event.taskDone();
        return event;
    }
}
