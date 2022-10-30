package book;

/**
 * RuntimeException is the superclass of those exceptions that can be thrown during
 * the normal operation of the Java Virtual Machine.
 */
public class IllegalIsbnException extends RuntimeException{
    public IllegalIsbnException() {
        super();
    }
    public IllegalIsbnException(String message) {
        super(message);
    }
}
