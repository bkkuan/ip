package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command that deletes a task at a given index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} by parsing the input to determine which task to delete.
     *
     * @param input the command input, e.g., "delete 2".
     */
    public DeleteCommand(final String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete command by removing the specified task.
     *
     * @param taskManager the task manager.
     * @param ui the user interface.
     * @param storage the storage object to persist tasks.
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        String msg = taskManager.deleteTask(index);
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
