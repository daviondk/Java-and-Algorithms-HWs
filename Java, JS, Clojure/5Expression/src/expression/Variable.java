package expression;

public class Variable implements OverallExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public double evaluate(double x) {
        return x;
    }

    public int evaluate(int x) {
        return x;
    }
}
