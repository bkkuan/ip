package seedu.bryan.command;

import bryan.command.Command;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.tasks.Tasks;
import bryan.ui.Ui;

import java.util.ArrayList;

/**
 * Command that finds tasks by searching for a keyword in the task description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param input the command input, e.g., "find book".
     */
    public FindCommand(final String input) {
        this.keyword = input.substring(5).trim();
    }

    /**
     * Executes the find command by searching for tasks whose descriptions contain the keyword.
     *
     * @param taskManager the task manager containing tasks.
     * @param ui the user interface for displaying messages.
     * @param storage the storage object (unused in this command).
     */
    @Override
    public void execute(final TaskManager taskManager, final Ui ui, final Storage storage) {
        final ArrayList<Tasks> matchingTasks = taskManager.findTasks(keyword);
        final StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        if (matchingTasks.isEmpty()) {
            output.append("No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append((i + 1) + ". " + matchingTasks.get(i).toString() + "\n");
            }
        }
        ui.showMessage(output.toString());
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
