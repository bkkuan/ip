package bryan.storage;

import bryan.tasks.Deadline;
import bryan.tasks.Event;
import bryan.tasks.Tasks;
import bryan.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    @Test
    void saveAndLoadTasks_success() throws IOException {
        // Arrange
        Path tempFile = Files.createTempFile("tasks", ".txt");
        Storage storage = new Storage(tempFile.toString());

        ArrayList<Tasks> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Read book"));
        tasksToSave.add(new Deadline("Return book", LocalDate.now()));
        tasksToSave.add(new Event("Project meeting", "2023-10-01", "2023-10-02"));

        // Act
        storage.save(tasksToSave);
        ArrayList<Tasks> loadedTasks = storage.load();

        // Assert
        assertEquals(tasksToSave.size(), loadedTasks.size(), "Number of tasks saved and loaded should match.");

        // Clean up
        Files.deleteIfExists(tempFile);
    }
}
