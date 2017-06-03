package com.edonoxako.mathtest.evaluator.expressiontree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by edono on 21.05.2017.
 */
public abstract class SequenceExpressionNode implements ExpressionNode {

    public class Term {
        public boolean positive;
        public ExpressionNode expression;

        public Term(boolean positive, ExpressionNode expression) {
            super();
            this.positive = positive;
            this.expression = expression;
        }

        @Override
        public String toString() {
            return expression.toString();
        }
    }

    protected LinkedList<Term> terms;

    public SequenceExpressionNode() {
        this.terms = new LinkedList<>();
    }

    public SequenceExpressionNode(ExpressionNode a, boolean positive) {
        this.terms = new LinkedList<>();
        this.terms.add(new Term(positive, a));
    }

    public void add(ExpressionNode a, boolean positive) {
        this.terms.add(new Term(positive, a));
    }

    protected List<Term> sortTerms() {
        return terms.stream()
                .sorted(Comparator.comparing(Term::toString))
                .collect(Collectors.toList());
    }
}
