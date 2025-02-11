package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} by parsing the task index from the input.
     *
     * @param input the command input, e.g., "mark 1".
     */
    public MarkCommand(final String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param taskManager the task manager.
     * @param ui the user interface.
     * @param storage the storage object to persist tasks.
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        String msg = taskManager.markTask(index);
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
