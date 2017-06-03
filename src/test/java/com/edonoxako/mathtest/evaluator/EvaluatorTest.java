package com.edonoxako.mathtest.evaluator;

import com.edonoxako.mathtest.evaluator.Evaluator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EvaluatorTest {

	@Test
	public void testSimpleFormulaTrueComparison() {
		Evaluator evaluator = Evaluator.from("a + b");
		assertTrue(evaluator.isEqual("a + b"));
		assertTrue(evaluator.isEqual("b + a"));
	}

	@Test
	public void testSimpleFormulaFalseComparison() throws Exception {
		Evaluator evaluator = Evaluator.from("a + b");
		assertFalse(evaluator.isEqual("a - b"));
	}

	@Test
	public void testComplexFormulaTrueComparison() throws Exception {
		Evaluator evaluator = Evaluator.from("(a + b)*(a - b)");
		assertTrue(evaluator.isEqual("(a + b)*(a - b)"));
		assertTrue(evaluator.isEqual("(a - b)*(b + a)"));
	}

	@Test
	public void testComplexFormulaFalseComparison() throws Exception {
		Evaluator evaluator = Evaluator.from("(a + b)*(a - b)");
		assertFalse(evaluator.isEqual("(b - a)*(b + a)"));
	}
}
