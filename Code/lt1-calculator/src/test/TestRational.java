package test;

import java.lang.ArithmeticException;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import multiformat.Rational;

/**
 * JUnit Testcase to test Rational.
 * Note that this class uses 'annotations' (the @...). This is a Java 1.5 feature.
 * @author gebruiker
 *
 */
public class TestRational extends TestCase {
	Rational r;

	@Before
	public void setUp() {
		r = new Rational();
	}

	@Test
	public void testSimplify() {
		r.setNumerator(25);
		r.setDenominator(5);
		r.simplify();

		assertEquals(5.0, r.getNumerator());
		assertEquals(1.0, r.getDenominator());

		r.setNumerator(10);
		r.setDenominator(0.5);
		r.simplify();

		assertEquals(10.0, r.getNumerator());
		assertEquals(0.5, r.getDenominator());
	}

	@Test
	public void testCanonical() {
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();

		assertEquals(125.0, r.getNumerator());
		assertEquals(10.0, r.getDenominator());

		r.setNumerator(12.5);
		r.setDenominator(0.01);
		r.canonical();

		assertEquals(125.0, r.getNumerator());
		assertEquals(0.1, r.getDenominator());
	}

	@Test
	public void testCanonicalAndSimplify() {
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();
		r.simplify();

		assertEquals(25.0, r.getNumerator());
		assertEquals(2.0, r.getDenominator());
	}

	@Test
	public void testDivideByZero() {
		Rational dividend = new Rational(5.0);
		Rational divisor = new Rational();

		boolean thrown = false;

		try {
			dividend.div(divisor);
		} catch (ArithmeticException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
	}
}
