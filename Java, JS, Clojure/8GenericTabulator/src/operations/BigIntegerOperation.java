package operations;

import exceptions.IllegalOperationException;
import exceptions.IncorrectConstException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {
    public BigInteger parseNumber(String number) throws IncorrectConstException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException NFE) {
            throw new IncorrectConstException();
        }
    }

    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    public BigInteger sub(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    public BigInteger mul(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    private void checkZero(BigInteger x, String reason) throws IllegalOperationException {
        if (x.equals(BigInteger.ZERO)) {
            throw new IllegalOperationException(reason);
        }
    }

    public BigInteger div(BigInteger x, BigInteger y) throws IllegalOperationException {
        checkZero(y, "Division by zero");
        return x.divide(y);
    }

}
