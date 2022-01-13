package mx.gob.sat.cfd._3;

/**
 * Created with IntelliJ IDEA.
 * User: Gerardo Aquino
 * Date: 3/06/13
 */
public class KeyException extends RuntimeException {

    public KeyException() {
        super();
    }

    public KeyException(String message) {
        super(message);
    }

    public KeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyException(Throwable cause) {
        super(cause);
    }
}
