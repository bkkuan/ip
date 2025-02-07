package bryan.command;

import bryan.exception.BryanException;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

/**
 * Command that adds a new task.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs an {@code AddCommand} with the specified input.
     *
     * @param input the command input, e.g., "todo read book"
     */
    public AddCommand(final String input) {
        this.input = input;
    }

    /**
     * Executes the add command by adding a task and saving the updated list.
     *
     * @param taskManager the task manager
     * @param ui the user interface
     * @param storage the storage object to persist tasks
     * @throws BryanException if an error occurs when adding the task
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage)
            throws BryanException {
        taskManager.addTask(input);
        storage.save(taskManager.getTasks());
        ui.showMessage("Added task:\n  "
                + taskManager.getTasks().get(taskManager.getTasks().size() - 1));
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

