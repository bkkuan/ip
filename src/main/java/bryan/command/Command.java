package bryan.command;

import bryan.exception.BryanException;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws BryanException;
    public abstract boolean isExit();
}
