package bryan.taskmanager;

import bryan.exception.BryanException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    @Test
    void addTask_validTodo_success() throws BryanException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask("todo read book");
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    void addTask_invalidTodo_throwsException() {
        TaskManager taskManager = new TaskManager();
        assertThrows(BryanException.class, () -> taskManager.addTask("todo"));
    }
}
