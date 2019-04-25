package expression;

public class Const implements OverallExpression {
    private Number value;

    public Const(Number value) {
        this.value = value;
    }

    public double evaluate(double x) {
        return value.doubleValue();
    }

    public int evaluate(int x) {
        return value.intValue();
    }
}
