package dynProg.solvers;

import dynProg.Solver;

public class RecursiveSolver implements Solver {
  public boolean solve(int[] numbers, int sum) {
    if (sum == 0) {
      return true;
    }
    if (numbers.length == 0) {
      return false;
    }
    for (int i = 0; i < numbers.length; i++) {
      int[] subset = without(numbers, i);
      // Attempt to solve with and without the current number.
      if (solve(subset, sum) || solve(subset, sum - numbers[i])) {
        return true;
      }
    }
    return false;
  }

  private int[] without(int[] arr, int remove) {
    final int l = arr.length;
    int[] clone = new int[l - 1];
    int cloneI = 0;
    for (int i = 0; i < l; i++) {
      if (i != remove) {
        clone[cloneI] = arr[i];
        cloneI++;
      }
    }
    return clone;
  }

}
