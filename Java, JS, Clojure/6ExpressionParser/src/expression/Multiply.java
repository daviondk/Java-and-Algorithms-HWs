package expression;

public class Multiply extends Binary {

    public Multiply(TripleExpression arg1, TripleExpression arg2) {
        super(arg1, arg2);
    }

    public int apply(int arg1, int arg2) {
        return arg1 * arg2;
    }

}
