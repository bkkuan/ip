package bryan.command;

import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
