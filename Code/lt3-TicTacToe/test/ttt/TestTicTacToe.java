package ttt;

import junit.framework.*;
import org.junit.Test;

public class TestTicTacToe extends TestCase {
  private final int X = TicTacToe.HUMAN;
  private final int O = TicTacToe.COMPUTER;
  private final int I = TicTacToe.EMPTY;

  private TicTacToe ttt;

  public void setup() {
    this.ttt = new TicTacToe();
  }

  @Test
  public void testPositionValue() {
    this.setup();
    ttt.loadBoard(new int[][] {
      { X, X, I },
      { O, O, I },
      { X, O, I },
    });
    assertFalse(ttt.gameOver());

    this.setup();
    ttt.loadBoard(new int[][] {
      { X, O, O },
      { X, X, I },
      { O, O, X },
    });
    assertTrue(ttt.gameOver());
    assertEquals(ttt.winner(), "human");

    this.setup();
    ttt.loadBoard(new int[][] {
      { X, X, O },
      { O, O, X },
      { O, X, X },
    });
    assertTrue(ttt.gameOver());
    assertEquals(ttt.winner(), "computer");
  }

  @Test
  public void testChooseMove() {
    // Should pick the winning move
    this.setup();
    ttt.loadBoard(new int[][] {
      { X, X, I },
      { O, O, I },
      { I, I, I },
    });
    assertEquals(ttt.chooseMove(), 5);

    // Should prevent a winning move by the human player
    this.setup();
    ttt.loadBoard(new int[][] {
      { X, X, I },
      { O, I, I },
      { O, I, I },
    });
    assertEquals(ttt.chooseMove(), 2);
  }
}
