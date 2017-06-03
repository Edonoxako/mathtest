package com.edonoxako.mathtest.evaluator.expressiontree;

/**
 * Created by edono on 20.05.2017.
 */
public class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String format, Object args) {
        super(String.format(format, args));
    }
}
