package com.edonoxako.mathtest;

import com.edonoxako.mathtest.evaluator.expressiontree.ExpressionNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by edono on 21.05.2017.
 */
public class ExpressionTreeTest extends BaseExpressionTest {

    @Test
    public void testSimpleExpressionEvaluating() throws Exception {
        testEvaluationResult("34 + 22", "SUM(22, 34)");
    }

    @Test
    public void testComplexExpressionEvaluating() throws Exception {
        testEvaluationResult("5^2 + 35 - (3 * 10)", "SUM(35, -(MULT(10, 3)), POW(5, 2))");
    }

    @Test
    public void testAnotherComplexExpressionEvaluating() throws Exception {
        testEvaluationResult("sqrt(5^3 - 5^2)", "SQRT(SUM(-(POW(5, 2)), POW(5, 3)))");
    }

    @Test
    public void testExpressionWithDivision() throws Exception {
        testEvaluationResult("(a + b) / (d + c)", "MULT(SUM(a, b), (1/SUM(c, d)))");
    }

    @Test
    public void testExpressionWithComplexDivision() throws Exception {
        testEvaluationResult("(a + b) * 1/(c + d)", "MULT(SUM(a, b), (1/SUM(c, d)))");
    }

    @Test
    public void testExpressionMultiplication() throws Exception {
        testEvaluationResult("2*b*c*a", "MULT(2, a, b, c)");
    }

    @Test
    public void testExpressionNegativeExponentValue() throws Exception {
        testEvaluationResult("5^-1", "POW(5, SUM(-(1)))");
    }

    @Test
    public void testEquality() throws Exception {
        testEvaluationResult("sqrt(5 * 2) + sqrt(7 * 3)", "SUM(SQRT(MULT(2, 5)), SQRT(MULT(3, 7)))");
        testEvaluationResult("sqrt(7 * 3) + sqrt(5 * 2)", "SUM(SQRT(MULT(2, 5)), SQRT(MULT(3, 7)))");
    }

    private void testEvaluationResult(String expression, String expected) {
        ExpressionNode expressionTree = parser.parse(tokenize(expression));
        String evaluationResult =  expressionTree.toString();
        assertEquals(expected, evaluationResult);
    }
}
