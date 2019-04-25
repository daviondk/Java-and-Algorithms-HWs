package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {

    private enum Token {NUMBER, PLUS, MINUS, MUL, DIV, OPEN_BRACE, CLOSE_BRACE, VARIABLE, XOR, OR, AND}
    private String expression;
    private int index = 0;
    private Token curToken;
    private String variable;
    private int constant;
    private boolean flag = false;

    public TripleExpression parse(String expression) {
        this.expression = expression;
        TripleExpression ans = bitwiseOR();
        index = 0;
        return ans;
    }

    private void next() {
        while (Character.isWhitespace(nextChar()));
        index--;
        char ch = nextChar();
        switch (ch) {
            case '+':
                curToken = Token.PLUS;
                break;
            case '-':
                curToken = Token.MINUS;
                break;
            case '*':
                curToken = Token.MUL;
                break;
            case '/':
                curToken = Token.DIV;
                break;
            case '(':
                curToken = Token.OPEN_BRACE;
                break;
            case ')':
                curToken = Token.CLOSE_BRACE;
                break;
            case '&':
                curToken = Token.AND;
                break;
            case '|':
                curToken = Token.OR;
                break;
            case '^':
                curToken = Token.XOR;
                break;
            default:
                if (Character.isDigit(ch)) {
                    StringBuilder str = new StringBuilder();
                    while (Character.isDigit(ch)) {
                        str.append(ch);
                        ch = nextChar();
                    }
                    index--;
                    try {
                        constant = Integer.parseInt(str.toString());
                    } catch (NumberFormatException e) {
                        constant = -1;
                        flag = true;
                    }
                    curToken = Token.NUMBER;
                } else if (Character.isLetter(ch)) {
                    StringBuilder str = new StringBuilder();
                    while (Character.isLetter(ch)) {
                        str.append(ch);
                        ch = nextChar();
                    }
                    index--;
                    variable = str.toString();
                    curToken = Token.VARIABLE;
                }
                break;
        }
    }


    private char nextChar() {
        if (index >= expression.length()) {
            return '#';
        } else {
            return expression.charAt(index++);
        }
    }

    private TripleExpression unary() {
        next();
        TripleExpression ans = new Const(1);
        switch (curToken) {
            case NUMBER:
                ans = new Const(constant);
                next();
                break;
            case VARIABLE:
                ans = new Variable(variable);
                next();
                break;
            case MINUS:
                ans = new Subtract(new Const(0), unary());
                if (flag) {
                    ans = new Const(-2147483648);
                }
                flag = false;
                break;
            case OPEN_BRACE:
                ans = bitwiseOR();
                next();
                break;
        }
        return ans;
    }

    private TripleExpression mulDiv() {
        TripleExpression cur = unary();
        while (true) {
            switch (curToken) {
                case DIV:
                    cur = new Divide(cur, unary());
                    break;
                case MUL:
                    cur = new Multiply(cur, unary());
                    break;
                default:
                    return cur;
            }
        }
    }

    private TripleExpression addSub() {
        TripleExpression cur = mulDiv();
        while (true) {
            switch (curToken) {
                case MINUS:
                    cur = new Subtract(cur, mulDiv());
                    break;
                case PLUS:
                    cur = new Add(cur, mulDiv());
                    break;
                default:
                    return cur;
            }
        }
    }

    private TripleExpression bitwiseAND() {
        TripleExpression cur = addSub();
        while (true) {
            switch (curToken) {
                case AND:
                    cur = new AND(cur, addSub());
                    break;
                default:
                    return cur;
            }
        }
    }

    private TripleExpression bitwiseXOR() {
        TripleExpression cur = bitwiseAND();
        while (true) {
            switch (curToken) {
                case XOR:
                    cur = new XOR(cur, bitwiseAND());
                    break;
                default:
                    return cur;
            }
        }
    }

    private TripleExpression bitwiseOR() {
        TripleExpression cur = bitwiseXOR();
        while (true) {
            switch (curToken) {
                case OR:
                    cur = new OR(cur, bitwiseXOR());
                    break;

                default:
                    return cur;
            }
        }
    }
}
