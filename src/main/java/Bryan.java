package seedu.bryan;

import bryan.command.Command;
import bryan.exception.BryanException;
import bryan.parser.Parser;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import bryan.ui.Ui;

/**
 * Main class for Bryan.
 * Initializes the application and starts the command loop.
 */
public class Bryan {
    private final Storage storage;
    private final TaskManager taskManager;
    private final Ui ui;

    /**
     * Constructs the Bryan application with the specified file path for storage.
     *
     * @param filePath the path to the data file
     */
    public Bryan(final String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.load());
    }

    /**
     * Runs the application by continuously reading and executing user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                final String input = ui.readCommand();
                final Command command = Parser.parse(input);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (final BryanException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        new Bryan("data/bryan.txt").run();
    }
}
