package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public class ConstantExpressionNode implements ExpressionNode {

    private String value;

    public ConstantExpressionNode(double value) {
        this.value = String.valueOf(value);
    }

    public ConstantExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getType() {
        return CONSTANT_NODE;
    }

    @Override
    public String toString() {
        return value;
    }
}
