package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public class MultiplicationExpressionNode extends SequenceExpressionNode {

    public MultiplicationExpressionNode() {
        super();
    }

    public MultiplicationExpressionNode(ExpressionNode a,
                                        boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return MULTIPLICATION_NODE;
    }

    public String getValue() {
        String prod = "";
        for (Term t : terms) {
            if (t.positive)
                prod += "*" + t.expression.getValue();
            else
                prod += "*(1/" + t.expression.getValue() + ")";
        }
        return prod;
    }

    @Override
    public String toString() {
        String prod = "MULT(";
        String delimeter = "";
        for (Term t : sortTerms()) {
            if (t.expression.toString().equals("1")) continue;
            if (t.positive)
                prod += delimeter + t.expression.toString();
            else
                prod += delimeter + "(1/" + t.expression.toString() + ")";
            delimeter = ", ";
        }
        return prod + ")";
    }
}
