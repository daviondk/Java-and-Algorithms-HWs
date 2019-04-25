package expressions;

import exceptions.OverflowException;
import operations.Operation;

public class Add<T> extends AbstractBinaryOperator<T> {
    public Add(TripleExpression<T> x, TripleExpression<T> y, Operation<T> z) {
        super(x, y, z);
    }

    protected T operate(T x, T y) throws OverflowException {
        return op.add(x, y);
    }
}
