package com.edonoxako.mathtest.evaluator;

import com.edonoxako.mathtest.evaluator.expressiontree.ExpressionNode;

/**
 * Created by edono on 20.05.2017.
 */
public class Evaluator {

    private ExpressionNode expressionTree;
    private Tokenizer tokenizer;
    private Parser parser;

    private Evaluator(ExpressionNode expressionTree, Tokenizer tokenizer, Parser parser) {
        this.expressionTree = expressionTree;
        this.tokenizer = tokenizer;
        this.parser = parser;
    }

    public boolean isEqual(String expressionToCompare) {
        tokenizer.tokenize(expressionToCompare);
        ExpressionNode expressionToCompareTree = parser.parse(tokenizer.getTokens());
        return expressionToCompareTree.toString().equals(expressionTree.toString());
    }

    public static Evaluator from(String expression) {
        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();

        tokenizer.add("sin|cos|exp|ln|sqrt", Tokenizer.Token.FUNCTION); // function
        tokenizer.add("\\(", Tokenizer.Token.OPEN_BRACKET); // open bracket
        tokenizer.add("\\)", Tokenizer.Token.CLOSE_BRACKET); // close bracket
        tokenizer.add("[+-]", Tokenizer.Token.PLUSMINUS); // plus or minus
        tokenizer.add("[*/]", Tokenizer.Token.MULTDIV); // mult or divide
        tokenizer.add("\\^", Tokenizer.Token.RAISED); // raised
        tokenizer.add("[0-9]+", Tokenizer.Token.NUMBER); // integer number
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", Tokenizer.Token.VARIABLE); // variable

        tokenizer.tokenize(expression);
        ExpressionNode expressionNode = parser.parse(tokenizer.getTokens());

        return new Evaluator(expressionNode, tokenizer, parser);
    }
}
