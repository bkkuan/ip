package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing a goodbye message.
     *
     * @param taskManager the task manager.
     * @param ui the user interface.
     * @param storage the storage object (unused in this command).
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command causes the application to exit.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
