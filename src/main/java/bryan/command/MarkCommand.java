package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.markTask(index);
        storage.save(taskManager.getTasks());
        ui.showMessage("Marked task " + (index + 1) + " as done.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
