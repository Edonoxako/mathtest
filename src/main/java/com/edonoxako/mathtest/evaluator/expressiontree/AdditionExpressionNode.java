package com.edonoxako.mathtest.evaluator.expressiontree;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by edono on 21.05.2017.
 */
public class AdditionExpressionNode extends SequenceExpressionNode {

    public AdditionExpressionNode() {
        super();
    }

    public AdditionExpressionNode(ExpressionNode a,
                                  boolean positive) {
        super(a, positive);
    }

    @Override
    public int getType() {
        return ADDITION_NODE;
    }

    @Override
    public String getValue() {
        String sum = "";
        for (Term t : terms) {
            if (t.positive)
                sum += "+" + t.expression.getValue();
            else
                sum += "-(" + t.expression.getValue() + ")";
        }
        return sum;
    }

    @Override
    public String toString() {
        String sum = "SUM(";
        String delimeter = "";
        for (Term t : sortTerms()) {
            if (t.positive)
                sum += delimeter + t.expression.toString();
            else
                sum += delimeter + "-(" + t.expression.toString() + ")";
            delimeter = ", ";
        }
        return sum + ")";
    }
}
