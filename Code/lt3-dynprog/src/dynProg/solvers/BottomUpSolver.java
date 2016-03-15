package dynProg.solvers;

import dynProg.Solver;

public class BottomUpSolver implements Solver {
  private boolean[][] createMatrix(int rows, int columns) {
    boolean[][] matrix = new boolean[rows][columns];

    // The first N numbers can _always_ sum to zero by just not using any of them.
    for (int i = 0; i < rows; i++) {
      matrix[i][0] = true;
    }
    // The first 0 numbers can never sum to a non-zero number.
    for (int j = 1; j < columns; j++) {
      matrix[0][j] = false;
    }

    return matrix;
  }

  public boolean solve(int[] numbers, int sum) {
    final int rows = numbers.length + 1;
    final int columns = sum + 1;

    boolean[][] matrix = this.createMatrix(rows, columns);

    // Walk top to bottom 👇 through the matrix.
    for (int currentRow = 1; currentRow <= numbers.length; currentRow++) {
      // Walk left to right 👉 through the matrix.
      // (The Wachowski Sisters would be proud!)
      for (int targetSubSum = 1; targetSubSum <= sum; targetSubSum++) {
        boolean[] lastRow = matrix[currentRow - 1];
        // If we could also make this sum with the previous N numbers, we don't
        // need to attempt to use our new number.
        boolean value = lastRow[targetSubSum];
        if (!value) {
          // Otherwise, this checks if (anything on the previous row + the
          // current number) counts up to the current target subsum.
          int lastRowIndex = targetSubSum - numbers[currentRow - 1];
          if (lastRowIndex >= 0) {
            value = lastRow[lastRowIndex];
          }
        }
        matrix[currentRow][targetSubSum] = value;
      }
    }

    // The result is in the bottom right of the matrix.
    return matrix[numbers.length][sum];
  }
}
