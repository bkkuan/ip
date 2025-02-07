package bryan.exception;

/**
 * Custom exception class for errors occurring in Bryan.
 */
public class BryanException extends Exception {

    /**
     * Constructs a new {@code BryanException} with the specified detail message.
     *
     * @param message the detail message
     */
    public BryanException(final String message) {
        super(message);
    }
}
