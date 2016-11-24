package exception;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatException extends Exception{
    public ChatException() {
    }

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
