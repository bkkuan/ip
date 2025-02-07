package bryan.taskmanager;

import bryan.exception.BryanException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying the functionality of the TaskManager class.
 */
class TaskManagerTest {
    /**
     * Tests adding a valid todo task.
     *
     * @throws BryanException if adding the task fails
     */
    @Test
    void addTask_validTodo_success() throws BryanException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask("todo read book");
        assertEquals(1, taskManager.getTasks().size());
    }

    /**
     * Tests that adding an invalid todo task throws an exception.
     */
    @Test
    void addTask_invalidTodo_throwsException() {
        TaskManager taskManager = new TaskManager();
        assertThrows(BryanException.class, () -> taskManager.addTask("todo"));
    }
}
