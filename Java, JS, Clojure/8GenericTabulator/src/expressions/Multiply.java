package expressions;

import exceptions.OverflowException;
import operations.Operation;

public class Multiply<T> extends AbstractBinaryOperator<T> {
    public Multiply(TripleExpression<T> x, TripleExpression<T> y, Operation<T> z) {
        super(x, y, z);
    }

    protected T operate(T x, T y) throws OverflowException {
        return op.mul(x, y);
    }
}
