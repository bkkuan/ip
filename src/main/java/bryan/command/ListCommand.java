package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by printing all tasks.
     *
     * @param taskManager the task manager containing tasks
     * @param ui          the user interface for output
     * @param storage     the storage object (unused in this command)
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.printTasks();
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
