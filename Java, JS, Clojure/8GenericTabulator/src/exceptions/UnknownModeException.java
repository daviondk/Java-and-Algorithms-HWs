package exceptions;

public class UnknownModeException extends Exception{
    public UnknownModeException(String mode) {
        super("Unknown mode for tabulation: " + mode);
    }
}
