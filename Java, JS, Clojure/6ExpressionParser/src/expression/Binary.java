package expression;

abstract public class Binary implements TripleExpression {
    private TripleExpression arg1;
    private TripleExpression arg2;

    protected Binary(TripleExpression arg1, TripleExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    abstract public int apply(int a, int b);

    public int evaluate(int x, int y, int z) {
        return apply(arg1.evaluate(x, y, z), arg2.evaluate(x, y, z));
    }
}
