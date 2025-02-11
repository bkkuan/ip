package seedu.bryan;

import bryan.command.Command;
import bryan.exception.BryanException;
import bryan.parser.Parser;
import bryan.storage.Storage;
import bryan.taskmanager.TaskManager;
import seedu.bryan.ui.GuiUi;

/**
 * Main class for Bryan.
 * Initializes the application and processes commands.
 */
public class Bryan {
    private final Storage storage;
    private final TaskManager taskManager;
    private final GuiUi ui;

    /**
     * Constructs the Bryan application with the specified file path for storage.
     *
     * @param filePath the path to the data file.
     */
    public Bryan(final String filePath) {
        ui = new GuiUi();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.load());
    }

    /**
     * Processes a user command and returns Bryan's response.
     *
     * @param input the user's command input.
     * @return a response string.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(taskManager, ui, storage);
            return ui.getMessage();
        } catch (BryanException e) {
            return e.getMessage();
        }
    }
}
