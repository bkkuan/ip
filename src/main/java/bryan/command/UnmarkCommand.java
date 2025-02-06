package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.unmarkTask(index);
        storage.save(taskManager.getTasks());
        ui.showMessage("Marked task " + (index + 1) + " as not done.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
