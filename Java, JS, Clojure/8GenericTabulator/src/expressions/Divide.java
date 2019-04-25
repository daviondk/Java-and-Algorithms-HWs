package expressions;

import exceptions.EvaluatingException;
import operations.Operation;

public class Divide<T> extends AbstractBinaryOperator<T> {
    public Divide(TripleExpression<T> x, TripleExpression<T> y, Operation<T> z) {
        super(x, y, z);
    }

    protected T operate(T x, T y) throws EvaluatingException {
        return op.div(x, y);
    }
}
