package expressions;


import exceptions.EvaluatingException;
import operations.Operation;

public abstract class AbstractUnaryOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> operand;
    protected Operation<T> op;

    protected AbstractUnaryOperator(TripleExpression<T> x, Operation<T> y) {
        operand = x;
        op = y;
    }

    protected abstract T operate(T x) throws EvaluatingException;

    public T evaluate(T x, T y, T z) throws EvaluatingException {
        return operate(operand.evaluate(x, y, z));
    }
}
