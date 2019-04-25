package expression;

public class Multiply extends AbstractExpression {
    public Multiply(OverallExpression leftOp, OverallExpression rightOp) {
        super(leftOp, rightOp);
    }

    protected double dCompute(double l, double r) {
        return l * r;
    }

    protected int iCompute(int l, int r) {
        return l * r;
    }
}
