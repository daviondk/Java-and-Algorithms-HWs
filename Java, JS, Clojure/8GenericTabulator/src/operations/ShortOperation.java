package operations;

import exceptions.IllegalOperationException;
import exceptions.IncorrectConstException;

public class ShortOperation implements Operation<Short> {
    public Short parseNumber(String number) throws IncorrectConstException {
        try {
            return (short) Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }

    private void checkZero(short y, String reason) throws IllegalOperationException {
        if (y == 0) {
            throw new IllegalOperationException(reason);
        }
    }

    public Short add(Short x, Short y) {
        return (short) (x + y);
    }

    public Short sub(Short x, Short y) {
        return (short) (x - y);
    }

    public Short mul(Short x, Short y) {
        return (short) (x * y);
    }

    public Short div(Short x, Short y) throws IllegalOperationException {
        checkZero(y, "Division by zero");
        return (short) (x / y);
    }
    public int count(Short x) {
        return Integer.bitCount(x);
    }
}
