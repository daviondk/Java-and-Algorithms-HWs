package expression;

import myExceptions.IllegalOperationException;

public class CheckedLog extends AbstractBinaryOperator {
    public CheckedLog(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected void check(final int x, final int y) throws IllegalOperationException {
        if (x <= 0) {
            throw new IllegalOperationException("Log form negative");
        }
        if (y <= 0 || y == 1) {
            throw new IllegalOperationException("Incorrect log base");
        }
    }

    protected int apply(int x, final int y) throws IllegalOperationException {
        check(x, y);
        int res = 0;
        while (x / 10 >= 1) {
            ++res;
            x /= 10;
        }
        return res;
    }
}