package expression;

public class Divide extends AbstractExpression {
    public Divide(OverallExpression leftOp, OverallExpression rightOp) {
        super(leftOp, rightOp);
    }

    protected double dCompute(double l, double r) {
        return l / r;
    }

    protected int iCompute(int l, int r) {
        return l / r;
    }
}
