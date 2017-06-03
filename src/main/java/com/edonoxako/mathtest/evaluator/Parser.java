package com.edonoxako.mathtest.evaluator;

import com.edonoxako.mathtest.evaluator.expressiontree.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by edono on 20.05.2017.
 */
public class Parser {

    private LinkedList<Tokenizer.Token> tokens;
    private Tokenizer.Token lookahead;

    public ExpressionNode parse(List<Tokenizer.Token> tokens) {
        this.tokens = new LinkedList<>(tokens);
        lookahead = this.tokens.getFirst();

        ExpressionNode expr = expression();

        if (lookahead.token != Tokenizer.Token.EPSILON)
            throw new ParserException("Unexpected symbol %s found", lookahead);

        return expr;
    }

    private void nextToken() {
        tokens.pop();
        // at the end of input we return an epsilon token
        if (tokens.isEmpty())
            lookahead = new Tokenizer.Token(Tokenizer.Token.EPSILON, "");
        else
            lookahead = tokens.getFirst();
    }

    private ExpressionNode expression() {
        // expression -> signed_term sum_op
        ExpressionNode expr = signedTerm();
        return sumOp(expr);
    }

    private ExpressionNode sumOp(ExpressionNode expression) {
        if (lookahead.token == Tokenizer.Token.PLUSMINUS) {
            // sum_op -> PLUSMINUS term sum_op
            AdditionExpressionNode sum;
            if (expression.getType() == ExpressionNode.ADDITION_NODE)
                sum = (AdditionExpressionNode)expression;
            else
                sum = new AdditionExpressionNode(expression, true);

            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            sum.add(t, positive);

            return sumOp(sum);
        } else {
            // sum_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode signedTerm() {
        if (lookahead.token == Tokenizer.Token.PLUSMINUS) {
            // signed_term -> PLUSMINUS term
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        } else {
            // signed_term -> term
            return term();
        }
    }

    private ExpressionNode term() {
        // term -> factor term_op
        ExpressionNode f = factor();
        return termOp(f);
    }

    private ExpressionNode termOp(ExpressionNode expression) {
        if (lookahead.token == Tokenizer.Token.MULTDIV) {
            // term_op -> MULTDIV factor term_op
            MultiplicationExpressionNode prod;

            if (expression.getType() == ExpressionNode.MULTIPLICATION_NODE)
                prod = (MultiplicationExpressionNode)expression;
            else
                prod = new MultiplicationExpressionNode(expression, true);

            boolean positive = lookahead.sequence.equals("*");
            nextToken();
            ExpressionNode f = signedFactor();
            prod.add(f, positive);

            return termOp(prod);
        } else {
            // term_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode signedFactor() {
        if (lookahead.token == Tokenizer.Token.PLUSMINUS) {
            // signed_factor -> PLUSMINUS factor
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = factor();

            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        } else {
            // signed_factor -> factor
            return factor();
        }
    }

    private ExpressionNode factor() {
        // factor -> argument factor_op
        ExpressionNode a = argument();
        return factorOp(a);
    }

    private ExpressionNode factorOp(ExpressionNode expression) {
        if (lookahead.token == Tokenizer.Token.RAISED) {
            // factor_op -> RAISED expression
            nextToken();
            ExpressionNode exponent = signedFactor();
            return new ExponentiationExpressionNode(expression, exponent);
        } else {
            // factor_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode argument() {
        if (lookahead.token == Tokenizer.Token.FUNCTION) {
            // argument -> FUNCTION argument
            int function = FunctionExpressionNode.stringToFunction(lookahead.sequence);
            nextToken();
            ExpressionNode expr = argument();
            return new FunctionExpressionNode(function, expr);
        }

        if (lookahead.token == Tokenizer.Token.OPEN_BRACKET) {
            // argument -> OPEN_BRACKET sum CLOSE_BRACKET
            nextToken();
            ExpressionNode expr = expression();

            if (lookahead.token != Tokenizer.Token.CLOSE_BRACKET)
                throw new ParserException("Closing brackets expected", lookahead);

            nextToken();
            return expr;
        } else {
            // argument -> value
            return value();
        }
    }

    private ExpressionNode value() {
        if (lookahead.token == Tokenizer.Token.NUMBER) {
            // argument -> NUMBER
            ExpressionNode expr = new ConstantExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }

        if (lookahead.token == Tokenizer.Token.VARIABLE) {
            // argument -> VARIABLE
            ExpressionNode expr = new VariableExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }

        if (lookahead.token == Tokenizer.Token.EPSILON)
            throw new ParserException("Unexpected end of input");
        else
            throw new ParserException("Unexpected symbol %s found", lookahead);
    }
}
