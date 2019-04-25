package expression;

public class Const implements TripleExpression {

    private Number arg;

    public Const(Number arg) {
        this.arg = arg;
    }

    public int evaluate(int x, int y, int z) {
        return arg.intValue();
    }

}
