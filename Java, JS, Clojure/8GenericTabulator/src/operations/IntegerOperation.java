package operations;

import exceptions.IllegalOperationException;
import exceptions.IncorrectConstException;
import exceptions.OverflowException;

public class IntegerOperation implements Operation<Integer> {
    private boolean checked;

    public IntegerOperation(boolean check) {
        checked = check;
    }

    public Integer parseNumber(String number) throws IncorrectConstException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }

    private void checkAdd(Integer x, Integer y) throws OverflowException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException();
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException();
        }
    }

    public Integer add(Integer x, Integer y) throws OverflowException {
        if (checked) {
            checkAdd(x, y);
        }
        return x + y;
    }

    private void checkSub(Integer x, Integer y) throws OverflowException {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowException();
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowException();
        }
    }

    public Integer sub(Integer x, Integer y) throws OverflowException {
        if (checked) {
            checkSub(x, y);
        }
        return x - y;
    }

    private void checkMul(Integer x, Integer y) throws OverflowException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException();
        }
    }

    public Integer mul(Integer x, Integer y) throws OverflowException {
        if (checked) {
            checkMul(x, y);
        }
        return x * y;
    }

    private void checkDiv(Integer x, Integer y) throws OverflowException {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
    }

    private void checkZero(int y, String reason) throws IllegalOperationException {
        if (y == 0) {
            throw new IllegalOperationException(reason);
        }
    }

    public Integer div(Integer x, Integer y) throws IllegalOperationException, OverflowException {
        checkZero(y, "Division by zero");
        if (checked) {
            checkDiv(x, y);
        }
        return x / y;
    }

    private void checkNot(Integer x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }
}
