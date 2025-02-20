package seedu.bryan.command;

import bryan.command.Command;
import bryan.exception.BryanException;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Command that snoozes (postpones) a deadline task by updating its due date.
 */
public class SnoozeCommand extends Command {
    private final int index;
    private final String newDateString;

    /**
     * Constructs a SnoozeCommand with the specified input.
     * Expected format: "snooze [task number] [new date]".
     *
     * @param input the command input.
     * @throws BryanException if the input format is invalid.
     */
    public SnoozeCommand(final String input) throws BryanException {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new BryanException("Invalid snooze command format. Use: snooze [task number] [yyyy-mm-dd]");
        }
        try {
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BryanException("Task number must be an integer.");
        }
        this.newDateString = parts[2].trim();
    }

    /**
     * Executes the snooze command by updating the due date of a deadline task.
     *
     * @param taskManager the task manager.
     * @param ui the user interface.
     * @param storage the storage object.
     * @throws BryanException if the task is not a deadline or the date format is invalid.
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) throws BryanException {
        String msg = taskManager.snoozeTask(index, newDateString);
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
