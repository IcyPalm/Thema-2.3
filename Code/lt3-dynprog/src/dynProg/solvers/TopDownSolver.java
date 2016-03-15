package dynProg.solvers;

import java.util.Arrays;

import dynProg.Solver;

public class TopDownSolver implements Solver {
  public boolean solve(int[] numbers, int sum) {
    return solve(
      new boolean[numbers.length][sum + 1],
      numbers,
      sum
    );
  }

  public boolean solve(boolean[][] cache, int[] numbers, int sum) {
    if (sum == 0) {
      return true;
    }
    if (numbers.length == 0) {
      return false;
    }

    final int[] subset = Arrays.copyOf(numbers, numbers.length - 1);
    // Use cached value if available.
    boolean value = cache[numbers.length - 1][sum] ||
      // Otherwise, attempt to solve with or without the current number.
      solve(cache, subset, sum) ||
      solve(cache, subset, sum - numbers[numbers.length - 1]);

    // Cache value for the next iteration.
    cache[numbers.length - 1][sum] = value;

    return value;
  }
}
