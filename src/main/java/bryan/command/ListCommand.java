package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by returning all tasks.
     *
     * @param taskManager the task manager containing tasks.
     * @param ui the user interface for output.
     * @param storage the storage object (unused in this command).
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        String msg = taskManager.listTasks();
        ui.showMessage(msg);
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
