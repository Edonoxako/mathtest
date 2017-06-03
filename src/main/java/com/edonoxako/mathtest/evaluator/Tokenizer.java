package com.edonoxako.mathtest.evaluator;

import com.edonoxako.mathtest.evaluator.expressiontree.ParserException;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by edono on 20.05.2017.
 */
public class Tokenizer {

    private class TokenInfo {
        final Pattern regex;
        final int token;

        TokenInfo(Pattern regex, int token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }

    public static class Token {
        public static final int EPSILON = 0;
        public static final int PLUSMINUS = 1;
        public static final int MULTDIV = 2;
        public static final int RAISED = 3;
        public static final int FUNCTION = 4;
        public static final int OPEN_BRACKET = 5;
        public static final int CLOSE_BRACKET = 6;
        public static final int NUMBER = 7;
        public static final int VARIABLE = 8;

        public final int token;
        public final String sequence;

        public Token(int token, String sequence) {
            super();
            this.token = token;
            this.sequence = sequence;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "token=" + token +
                    ", sequence='" + sequence + '\'' +
                    '}';
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<>();
        tokens = new LinkedList<>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }

    public void tokenize(String str) {
        String s = str.trim();
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;
                }
            }
            if (!match) throw new ParserException("Unexpected character in input: "+s);
        }
    }

    public LinkedList<Token> getTokens()
    {
        return tokens;
    }
}
