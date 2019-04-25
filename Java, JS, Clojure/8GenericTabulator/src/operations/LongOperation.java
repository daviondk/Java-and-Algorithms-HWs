package operations;

import exceptions.IllegalOperationException;
import exceptions.IncorrectConstException;

public class LongOperation implements Operation<Long> {
    public Long parseNumber(String number) throws IncorrectConstException {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }

    private void checkZero(long y, String reason) throws IllegalOperationException {
        if (y == 0) {
            throw new IllegalOperationException(reason);
        }
    }

    public Long add(Long x, Long y) {
        return (x + y);
    }

    public Long sub(Long x, Long y) {
        return (x - y);
    }

    public Long mul(Long x, Long y) {
        return (x * y);
    }

    public Long div(Long x, Long y) throws IllegalOperationException {
        checkZero(y, "Division by zero");
        return (x / y);
    }

    public int count(Long x) {
        return Long.bitCount(x);
    }
}
