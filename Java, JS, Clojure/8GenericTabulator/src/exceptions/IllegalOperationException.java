package exceptions;

public class IllegalOperationException extends EvaluatingException {
    public IllegalOperationException(String message) {
        super("Illegar operation: " + message);
    }
}
