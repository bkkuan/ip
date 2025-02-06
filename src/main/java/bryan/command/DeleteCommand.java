package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.deleteTask(index);
        storage.save(taskManager.getTasks());
        ui.showMessage("Deleted task " + (index + 1) + ".");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
