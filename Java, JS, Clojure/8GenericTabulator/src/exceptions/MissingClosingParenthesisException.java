package exceptions;

public class MissingClosingParenthesisException extends ParsingException {
    public MissingClosingParenthesisException(String s, int ind){
        super("Missing closing parenthesis at position: " + ind + "\n" + s + "\n" + getPlace(ind, 1));
    }
}
