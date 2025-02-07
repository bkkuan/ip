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

/**
 * Test class for verifying the functionality of the Storage class.
 */
class StorageTest {

    /**
     * Tests that tasks saved by the Storage class can be correctly loaded.
     *
     * @throws IOException if an I/O error occurs during testing
     */
    @Test
    void saveAndLoadTasks_success() throws IOException {
        // Arrange
        final Path tempFile = Files.createTempFile("tasks", ".txt");
        final Storage storage = new Storage(tempFile.toString());
        final ArrayList<Tasks> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Read book"));
        tasksToSave.add(new Deadline("Return book", LocalDate.now()));
        tasksToSave.add(new Event("Project meeting", "2023-10-01", "2023-10-02"));

        // Act
        storage.save(tasksToSave);
        final ArrayList<Tasks> loadedTasks = storage.load();

        // Assert
        assertEquals(tasksToSave.size(), loadedTasks.size(),
                "Number of tasks saved and loaded should match.");

        // Clean up
        Files.deleteIfExists(tempFile);
    }
}

