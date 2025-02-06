package bryan.command;

import bryan.exception.BryanException;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;
import bryan.storage.Storage;

public class AddCommand extends Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws BryanException {
        taskManager.addTask(input);
        storage.save(taskManager.getTasks());
        ui.showMessage("Added task:\n  " + taskManager.getTasks().get(taskManager.getTasks().size() - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
