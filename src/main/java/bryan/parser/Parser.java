package bryan.parser;

import bryan.command.*;
import bryan.exception.BryanException;

/**
 * Parser class responsible for parsing user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Parses the given input string and returns a corresponding command.
     *
     * @param input the user input
     * @return the command corresponding to the input
     * @throws BryanException if the command is unknown or input is invalid
     */
    public static Command parse(final String input) throws BryanException {
        final String commandWord = input.split(" ")[0].toLowerCase();
        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(input);
            case "unmark":
                return new UnmarkCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(input);
            default:
                throw new BryanException("Unknown command");
        }
    }
}
