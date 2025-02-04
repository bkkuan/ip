import java.io.IOException;
import java.nio.file.*;
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
                if (parts.length < 3) continue;

                boolean isDone = parts[1].equals("1");
                Tasks task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        if (parts.length >= 4) task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        if (parts.length >= 5) task = new Event(parts[2], parts[3], parts[4]);
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
