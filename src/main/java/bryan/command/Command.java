package bryan.command;

import bryan.exception.BryanException;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Abstract class representing a command that can be executed by Bryan.
 */
public abstract class Command {

    /**
     * Executes the command with the given task manager, UI, and storage.
     *
     * @param taskManager the task manager containing the tasks
     * @param ui          the user interface for displaying messages
     * @param storage     the storage to save and load tasks
     * @throws BryanException if there is an error during execution
     */
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws BryanException;

    /**
     * Indicates whether the command should cause the application to exit.
     *
     * @return true if the command is an exit command, false otherwise
     */
    public abstract boolean isExit();
}
