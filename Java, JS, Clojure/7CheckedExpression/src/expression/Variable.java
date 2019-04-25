package expression;

public class Variable implements TripleExpression {
    private static String xName = null;
    private static String yName = null;
    private static String zName = null;
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    private String getName(String name) {
        if (xName == null || xName.equals(name)) {
            xName = name;
            return "x";
        } else if (yName == null || yName.equals(name)) {
            yName = name;
            return "y";
        } else {
            zName = name;
            return "z";
        }
    }

    public int evaluate(int x, int y, int z) {
        switch (getName(name)) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }
}