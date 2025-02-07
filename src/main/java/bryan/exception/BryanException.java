package bryan.exception;

/**
 * Custom exception class for errors occurring in Bryan.
 */
public class BryanException extends Exception {

    /**
     * Constructs a new BryanException with the specified detail message.
     *
     * @param message the detail message
     */
    public BryanException(String message) {
        super(message);
    }
}
