package exceptions;

public class OddOpeningParenthesisException extends ParsingException {
    public OddOpeningParenthesisException(String s, int ind) {
        super("Odd opening parenthesis at position: " + ind + "\n" + s + "\n" + getPlace(ind, 1));
    }
}
