package com.edonoxako.mathtest.evaluator;

import com.edonoxako.mathtest.evaluator.expressiontree.ParserException;
import org.junit.Test;

/**
 * Created by edono on 21.05.2017.
 */
public class ParserTest extends BaseExpressionTest {

    @Test
    public void testParserSimpleExpression() throws Exception {
        testParser("a + b");
    }

    @Test
    public void testParserComplexExpression() throws Exception {
        testParser("a^2 + ( 2*a*b - b^2 )");
    }

    @Test(expected = ParserException.class)
    public void testParserBadExpression() throws Exception {
        testParser("a+-b)");
    }

    @Test
    public void testParserSimpleNegativeExpression() throws Exception {
        testParser("-a");
    }

    private void testParser(String expression) {
        parser.parse(tokenize(expression));
    }
}
