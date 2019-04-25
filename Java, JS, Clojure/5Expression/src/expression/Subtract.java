package expression;

public class Subtract extends AbstractExpression {
    public Subtract(OverallExpression leftOp, OverallExpression rightOp) {
        super(leftOp, rightOp);
    }

    protected double dCompute(double l, double r) {
        return l - r;
    }

    protected int iCompute(int l, int r) {
        return l - r;
    }
}
