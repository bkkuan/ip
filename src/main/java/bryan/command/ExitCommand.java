package bryan.command;

import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true; // Indicates the program should exit
    }
}
