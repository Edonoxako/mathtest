package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public class VariableExpressionNode implements ExpressionNode {

    private String name;
    private String value;
    private boolean valueSet;

    public VariableExpressionNode(String name) {
        this.name = name;
        valueSet = false;
    }

    @Override
    public int getType() {
        return VARIABLE_NODE;
    }

    public void setValue(double value) {
        this.value = String.valueOf(value);
        this.valueSet = true;
    }

    @Override
    public String getValue() {
        if (valueSet)
            return value;
        else
            throw new EvaluationException("Variable '"
                    + name + "' was not initialized.");
    }

    @Override
    public String toString() {
        return name;
    }
}
