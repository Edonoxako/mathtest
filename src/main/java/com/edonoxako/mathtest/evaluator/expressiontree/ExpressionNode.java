package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public interface ExpressionNode {
    int VARIABLE_NODE = 1;
    int CONSTANT_NODE = 2;
    int ADDITION_NODE = 3;
    int MULTIPLICATION_NODE = 4;
    int EXPONENTIATION_NODE = 5;
    int FUNCTION_NODE = 6;

    int getType();
    String getValue();
}
