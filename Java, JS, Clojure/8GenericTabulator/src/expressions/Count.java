package expressions;

import exceptions.OverflowException;
import operations.Operation;

public class Count<T> extends AbstractUnaryOperator<T> {
    public Count(final TripleExpression<T> x, final Operation<T> y) {
        super(x, y);
    }

    protected T operate(T x) throws OverflowException {
        return op.count(x);
    }
}
