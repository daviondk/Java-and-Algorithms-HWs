package expression;

import myExceptions.IllegalOperationException;
import myExceptions.OverflowException;

public class CheckedPow extends AbstractMultiply {
    public CheckedPow(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }


    protected void externalCheck(final int x, final int y) throws IllegalOperationException {
        if (x == 0 && y == 0) {
            throw new IllegalOperationException("0 ** 0 is not determinated");
        }
        if (x != 1 && x != -1 && y < 0) {
            throw new IllegalOperationException("Powering in negative");
        }
    }

    protected int apply(final int x, final int y) throws OverflowException, IllegalOperationException {
        externalCheck(x, y);
        if (y < 0) {
            switch (x) {
                case 1:
                    return 1;
                case -1:
                    if (y % 2 == -1) {
                        return -1;
                    } else {
                        return 1;
                    }
                default:
                    return 0;
            }
        }
        int n = 0;
        int res = 1;
        while (n != y) {
            if (n == 0) {
                check(res, x);
                res *= x;
                n++;
                continue;
            }
            if (n * 2 <= y) {
                check(res, res);
                res *= res;
                n <<= 1;
            } else {
                check(res, x);
                res *= x;
                n++;
            }
        }
        return res;
    }
}