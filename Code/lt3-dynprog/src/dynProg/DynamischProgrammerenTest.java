package dynProg;

import dynProg.solvers.BottomUpSolver;
import dynProg.solvers.RecursiveSolver;
import dynProg.solvers.TopDownSolver;

import junit.framework.*;
import org.junit.Test;

public class DynamischProgrammerenTest extends TestCase {
	private int[] numbers = null;
	private int sum;
	private Solver solver;

	@Test
	public void testRecursive() {
		this.solver = new RecursiveSolver();
		this.doTest();
	}

	@Test
	public void testBottomUp() {
		this.solver = new BottomUpSolver();
		this.doTest();
	}

	@Test
	public void testTopDown() {
		this.solver = new TopDownSolver();
		this.doTest();
	}

	private void doTest() {
		// 3+5+9=17
		assertTrue(solver.solve(new int[] { 3, 5, 7, 9, 11 }, 17));
		// Lukt niet
		assertFalse(solver.solve(new int[] { 2, 4 }, 5));
		// Één te weinig
		assertFalse(solver.solve(new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 }, 31));
		// Precies goed
		assertTrue(solver.solve(new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 }, 31));
	}
}
