package bryan.parser;

import bryan.command.*;
import bryan.exception.BryanException;

public class Parser {
    public static Command parse(String input) throws BryanException {
        String commandWord = input.split(" ")[0].toLowerCase();
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
