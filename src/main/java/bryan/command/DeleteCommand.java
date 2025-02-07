package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

/**
 * Command that deletes a task at a given index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand by parsing the input to determine which task to delete.
     *
     * @param input the command input, e.g., "delete 2"
     */
    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete command by removing the specified task.
     *
     * @param taskManager the task manager
     * @param ui          the user interface
     * @param storage     the storage object to persist tasks
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.deleteTask(index);
        storage.save(taskManager.getTasks());
        ui.showMessage("Deleted task " + (index + 1) + ".");
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
