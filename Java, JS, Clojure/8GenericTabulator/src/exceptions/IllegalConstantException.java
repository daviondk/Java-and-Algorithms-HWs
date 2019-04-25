package exceptions;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(String reason, String s, int ind) {
        super("Constant is unsuitable for current mode: " + reason + "\n" + s + "\n" + getPlace(ind, reason.length()));
    }
}
