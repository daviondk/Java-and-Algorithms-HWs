package expression;

import myExceptions.EvaluatingException;
import myExceptions.OverflowException;

public abstract class AbstractMultiply extends AbstractBinaryOperator {
    protected AbstractMultiply(final TripleExpression x, final TripleExpression y) { super(x, y); }

    protected void check(final int x, final int y) throws OverflowException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException();
        }
    }

    protected abstract int apply(final int x, final int y) throws EvaluatingException;
}
