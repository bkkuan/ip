package bryan.parser;

import bryan.command.Command;
import bryan.exception.BryanException;

/**
 * Parser class responsible for parsing user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Parses the given input string and returns a corresponding command.
     *
     * @param input the user input.
     * @return the command corresponding to the input.
     * @throws BryanException if the command is unknown or input is invalid.
     */
    public static Command parse(final String input) throws BryanException {
        final String commandWord = input.split(" ")[0].toLowerCase();
        switch (commandWord) {
            case "bye":
                return new seedu.bryan.command.ExitCommand();
            case "list":
                return new seedu.bryan.command.ListCommand();
            case "mark":
                return new seedu.bryan.command.MarkCommand(input);
            case "unmark":
                return new seedu.bryan.command.UnmarkCommand(input);
            case "delete":
                return new seedu.bryan.command.DeleteCommand(input);
            case "find":
                return new seedu.bryan.command.FindCommand(input);
            case "todo":
            case "deadline":
            case "event":
                return new seedu.bryan.command.AddCommand(input);
            default:
                throw new BryanException("I'm not sure what you mean. May i know what do you want to do?");
        }
    }
}
