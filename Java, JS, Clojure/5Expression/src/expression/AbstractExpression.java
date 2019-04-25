package expression;

public abstract class AbstractExpression implements OverallExpression {
    OverallExpression leftOp, rightOp;

    protected AbstractExpression(OverallExpression leftOp, OverallExpression rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }


    protected abstract double dCompute(double l, double r);

    protected abstract int iCompute(int l, int r);

    public double evaluate(double x) {
        return dCompute(leftOp.evaluate(x), rightOp.evaluate(x));
    }

    public int evaluate(int x) {
        return iCompute(leftOp.evaluate(x), rightOp.evaluate(x));
    }
}
