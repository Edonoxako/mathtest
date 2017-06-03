package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public class ExponentiationExpressionNode implements ExpressionNode {

    private ExpressionNode base;
    private ExpressionNode exponent;

    public ExponentiationExpressionNode(ExpressionNode base,
                                        ExpressionNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public int getType() {
        return ExpressionNode.EXPONENTIATION_NODE;
    }

    public String getValue() {
        return base.getValue() + "^(" + exponent.getValue() + ")";
    }

    @Override
    public String toString() {
        return "POW(" + base.toString() + ", " + exponent.toString() + ")";
    }
}
