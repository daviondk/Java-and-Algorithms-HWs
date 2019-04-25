package expressions;

import exceptions.EvaluatingException;
import operations.Operation;

public abstract class AbstractBinaryOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> firstOperand;
    private TripleExpression<T> secondOperand;
    protected Operation<T> op;

    protected AbstractBinaryOperator(TripleExpression<T> x, TripleExpression<T> y, Operation<T> z) {
        firstOperand = x;
        secondOperand = y;
        op = z;
    }

    protected abstract T operate(T x, T y) throws EvaluatingException;

    public T evaluate(T x, T y, T z) throws EvaluatingException {
        return operate(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }

}
