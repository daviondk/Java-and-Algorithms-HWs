package expression;

import myExceptions.OverflowException;


public class CheckedMultiply extends AbstractMultiply {
    public CheckedMultiply(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws OverflowException {
        check(x, y);
        return x * y;
    }
}