package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} by parsing the task index from the input.
     *
     * @param input the command input, e.g., "unmark 1".
     */
    public UnmarkCommand(final String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param taskManager the task manager.
     * @param ui the user interface.
     * @param storage the storage object to persist tasks.
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        String msg = taskManager.unmarkTask(index);
        storage.save(taskManager.getTasks());
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
