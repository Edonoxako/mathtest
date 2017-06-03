package com.edonoxako.mathtest;

import com.edonoxako.mathtest.evaluator.Tokenizer;
import com.edonoxako.mathtest.evaluator.expressiontree.ParserException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by edono on 20.05.2017.
 */
public class TokenizerTest extends BaseExpressionTest {

    @Test
    public void testExpressionTokenizing() throws Exception {
        tokenizer.tokenize("a + b");

        assertEquals("a", tokenizer.getTokens().get(0).sequence);
        assertEquals(Tokenizer.Token.VARIABLE, tokenizer.getTokens().get(0).token);

        assertEquals("+", tokenizer.getTokens().get(1).sequence);
        assertEquals(Tokenizer.Token.PLUSMINUS, tokenizer.getTokens().get(1).token);

        assertEquals("b", tokenizer.getTokens().get(2).sequence);
        assertEquals(Tokenizer.Token.VARIABLE, tokenizer.getTokens().get(2).token);
    }

    @Test(expected = ParserException.class)
    public void testTokenizeWrongExpression() throws Exception {
        tokenizer.tokenize("a%2");
        System.out.println(tokenizer.getTokens());
    }
}
