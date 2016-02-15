/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package test;

import junit.framework.TestCase;
import org.junit.Test;

import multiformat.*;

public class TestCalculator extends TestCase {
	public TestCalculator(String arg0) {
		super(arg0);
	}

	@Test
	public void testOperations() {
		Calculator calc = new Calculator();

		try {
			calc.addOperand("3.2");
			assertEquals("0.0", calc.firstOperand());
			assertEquals("3.2", calc.secondOperand());

			calc.addOperand("2.8");
			assertEquals("3.2", calc.firstOperand());
			assertEquals("2.8", calc.secondOperand());

			calc.add();
			assertEquals("0.0", calc.firstOperand());
			assertEquals("6.0", calc.secondOperand());
		} catch (FormatException e) {
			fail("Unexpected format exception");
		}
	}

	@Test
	public void testStack() {
		Calculator calc = new Calculator();
		try {
			// 6 * (3 + 2)
			calc.addOperand("18.0");
			calc.addOperand("6.0");
			calc.addOperand("3.0");
			calc.addOperand("2.0");
			assertEquals("3.0", calc.firstOperand());
			assertEquals("2.0", calc.secondOperand());

			calc.add();
			assertEquals("6.0", calc.firstOperand());
			assertEquals("5.0", calc.secondOperand());
			calc.multiply();
			assertEquals("18.0", calc.firstOperand());
			assertEquals("30.0", calc.secondOperand());

			calc.delete();
			assertEquals("0.0", calc.firstOperand());
			assertEquals("18.0", calc.secondOperand());

			// Deleting is a no-op if the stack was empty
			calc.delete();
			assertEquals("0.0", calc.firstOperand());
			assertEquals("0.0", calc.secondOperand());

			calc.delete();
			assertEquals("0.0", calc.firstOperand());
			assertEquals("0.0", calc.secondOperand());
		} catch (FormatException e) {
			fail("Unexpected format exception");
		}
	}

	@Test
	public void testInvalidDigits() {
		Calculator calc = new Calculator();
		boolean thrown = false;
		try {
			calc.setBase(new DecimalBase());
			calc.addOperand("3");
			assertTrue(true);
			calc.addOperand("A");
			fail("Expected NumberBaseException");
		} catch (NumberBaseException e) {
			thrown = true;
		} catch (FormatException e) {
			fail("Expected NumberBaseException, got FormatException");
		} finally {
			assertTrue(thrown);
		}
	}
}
