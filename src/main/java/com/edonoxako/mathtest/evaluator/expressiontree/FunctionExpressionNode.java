package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 21.05.2017.
 */
public class FunctionExpressionNode implements ExpressionNode {

    public static final int SIN = 1;
    public static final int COS = 2;
    public static final int TAN = 3;

    public static final int ASIN = 4;
    public static final int ACOS = 5;
    public static final int ATAN = 6;

    public static final int SQRT = 7;
    public static final int EXP = 8;

    public static final int LN = 9;
    public static final int LOG = 10;
    public static final int LOG2 = 11;

    private int function;
    private ExpressionNode argument;

    public FunctionExpressionNode(int function, ExpressionNode argument) {
        super();
        this.function = function;
        this.argument = argument;
    }

    public int getType() {
        return FUNCTION_NODE;
    }

    public String getValue() {
        switch (function) {
            case SIN:  return "SIN(" + argument.getValue() + ")";
            case COS:  return "COS(" + argument.getValue() + ")";
            case TAN:  return "TAN(" + argument.getValue() + ")";
            case ASIN: return "ASIN(" + argument.getValue() + ")";
            case ACOS: return "ACOS(" + argument.getValue() + ")";
            case ATAN: return "ATAN(" + argument.getValue() + ")";
            case SQRT: return "SQRT(" + argument.getValue() + ")";
            case EXP:  return "EXP(" + argument.getValue() + ")";
            case LN:   return "LN(" + argument.getValue() + ")";
            case LOG:  return "LOG(" + argument.getValue() + ")";
            case LOG2: return "LOG2(" + argument.getValue() + ")";
        }
        throw new EvaluationException("Invalid function id " + function + "!");
    }

    public static int stringToFunction(String str) {
        if (str.equals("sin")) return FunctionExpressionNode.SIN;
        if (str.equals("cos")) return FunctionExpressionNode.COS;
        if (str.equals("tan")) return FunctionExpressionNode.TAN;

        if (str.equals("asin")) return FunctionExpressionNode.ASIN;
        if (str.equals("acos")) return FunctionExpressionNode.ACOS;
        if (str.equals("atan")) return FunctionExpressionNode.ATAN;

        if (str.equals("sqrt")) return FunctionExpressionNode.SQRT;
        if (str.equals("exp")) return FunctionExpressionNode.EXP;

        if (str.equals("ln")) return FunctionExpressionNode.LN;
        if (str.equals("log"))return FunctionExpressionNode.LOG;
        if (str.equals("log2")) return FunctionExpressionNode.LOG2;

        throw new ParserException("Unexpected Function " + str + " found!");
    }

    public static String getAllFunctions() {
        return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2";
    }

    @Override
    public String toString() {
        switch (function) {
            case SIN:  return "SIN(" + argument.toString() + ")";
            case COS:  return "COS(" + argument.toString() + ")";
            case TAN:  return "TAN(" + argument.toString() + ")";
            case ASIN: return "ASIN(" + argument.toString() + ")";
            case ACOS: return "ACOS(" + argument.toString() + ")";
            case ATAN: return "ATAN(" + argument.toString() + ")";
            case SQRT: return "SQRT(" + argument.toString() + ")";
            case EXP:  return "EXP(" + argument.toString() + ")";
            case LN:   return "LN(" + argument.toString() + ")";
            case LOG:  return "LOG(" + argument.toString() + ")";
            case LOG2: return "LOG2(" + argument.toString() + ")";
        }
        throw new EvaluationException("Invalid function id " + function + "!");
    }
}
