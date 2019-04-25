package operations;

import exceptions.IncorrectConstException;

public class DoubleOperation implements Operation<Double> {
    public Double parseNumber(String number) throws IncorrectConstException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException NFM) {
            throw new IncorrectConstException();
        }
    }

    public Double add(Double x, Double y) {
        return x + y;
    }

    public Double sub(Double x, Double y) {
        return x - y;
    }

    public Double mul(Double x, Double y) {
        return x * y;
    }

    public Double div(Double x, Double y) {
        return x / y;
    }

}
