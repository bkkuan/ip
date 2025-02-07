package seedu.bryan;

import bryan.command.Command;
import bryan.exception.BryanException;
import bryan.parser.Parser;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;


public class Bryan {
    private final Storage storage;
    private final TaskManager taskManager;
    private final Ui ui;

    public Bryan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (BryanException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bryan("data/bryan.txt").run();
    }
}
