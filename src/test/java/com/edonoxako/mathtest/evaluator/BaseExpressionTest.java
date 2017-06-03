package com.edonoxako.mathtest.evaluator;

import org.junit.Before;

import java.util.List;

/**
 * Created by edono on 21.05.2017.
 */
public abstract class BaseExpressionTest {

    protected Tokenizer tokenizer;
    protected Parser parser;

    @Before
    public void setUp() throws Exception {
        tokenizer = new Tokenizer();
        parser = new Parser();

        tokenizer.add("sin|cos|exp|ln|sqrt", Tokenizer.Token.FUNCTION); // function
        tokenizer.add("\\(", Tokenizer.Token.OPEN_BRACKET); // open bracket
        tokenizer.add("\\)", Tokenizer.Token.CLOSE_BRACKET); // close bracket
        tokenizer.add("[+-]", Tokenizer.Token.PLUSMINUS); // plus or minus
        tokenizer.add("[*/]", Tokenizer.Token.MULTDIV); // mult or divide
        tokenizer.add("\\^", Tokenizer.Token.RAISED); // raised
        tokenizer.add("[0-9]+", Tokenizer.Token.NUMBER); // integer number
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", Tokenizer.Token.VARIABLE); // variable
    }

    protected List<Tokenizer.Token> tokenize(String expression) {
        tokenizer.tokenize(expression);
        return tokenizer.getTokens();
    }
}
