package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

/**
 * Command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand by parsing the task index from the input.
     *
     * @param input the command input, e.g., "mark 1"
     */
    public MarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param taskManager the task manager
     * @param ui          the user interface
     * @param storage     the storage object to persist tasks
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.markTask(index);
        storage.save(taskManager.getTasks());
        ui.showMessage("Marked task " + (index + 1) + " as done.");
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

