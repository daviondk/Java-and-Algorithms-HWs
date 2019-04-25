package operations;

import exceptions.EvaluatingException;
import exceptions.IllegalOperationException;
import exceptions.IncorrectConstException;
import exceptions.OverflowException;

public interface Operation<T> {
    T parseNumber(String number) throws IncorrectConstException;

    T add(T x, T y) throws OverflowException;

    T sub(T x, T y) throws OverflowException;

    T mul(T x, T y) throws OverflowException;

    T div(T x, T y) throws EvaluatingException;

    int count(T x) throws OverflowException;
}
