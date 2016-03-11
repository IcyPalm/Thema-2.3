package ttt;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.IntStream;

class TicTacToe {
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;

	private int[][] board = new int[3][3];
	private Random random = new Random();
	private int side = random.nextInt(2);
	private int position = UNCLEAR;
	private char computerChar;
	private char humanChar;

	// Constructor
	public TicTacToe() {
		this.clearBoard();
		this.initSide();
	}

	public TicTacToe(int[][] board) {
		this.loadBoard(board);
		this.initSide();
	}

	public TicTacToe(int[][] board, int initialSide) {
		this.loadBoard(board);
		if (initialSide == COMPUTER) {
			this.setComputerPlays();
		} else {
			this.setHumanPlays();
		}
	}

	private void initSide() {
		if (this.side == COMPUTER) {
			computerChar = 'X';
			humanChar = 'O';
		} else {
			computerChar = 'O';
			humanChar = 'X';
		}
	}

	/**
	 * Initialise the board from a custom config. Best for testing 💪
	 *
	 * @param board Board configuration. Pass a two-dimensional array like:
	 *
	 *    new int[][] {
	 *      { HUMAN,    HUMAN,    COMPUTER },
	 *      { COMPUTER, HUMAN,    COMPUTER },
	 *      { HUMAN,    COMPUTER, EMPTY    }
	 *    }
	 */
	public void loadBoard(int[][] board) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				this.place(row, col, board[row][col]);
			}
		}
	}

	public void setComputerPlays() {
		this.side = COMPUTER;
		initSide();
	}

	public void setHumanPlays() {
		this.side = HUMAN;
		initSide();
	}

	public boolean computerPlays() {
		return side == COMPUTER;
	}

	public int chooseMove() {
		Best best = chooseMove(COMPUTER);
		if (best != null) {
			return best.row * 3 + best.column;
		}
		return 0;
	}

	// Find optimal move
	private Best chooseMove(int side) {
		int opp;							// The other side
		Best reply;					 // Opponent's best reply
		int simpleEval;			 // Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
		int value;

		if ((simpleEval = positionValue()) != UNCLEAR) {
			return new Best(simpleEval);
		}

		return this.minimax(0);
	}

	/**
	 * Get the best available move using the minimax algorithm.
	 *
	 * @param depth How many moves into the future we're looking.
	 * @return The best available move.
	 */
	private Best minimax(int depth) {
		if (this.gameOver()) {
			return new Best(this.score(depth));
		}

		Collection<Integer> available = this.availableMoves();
		Stream<Best> moves = available.stream().map(move -> {
			TicTacToe child = this.clone().playMove(move);
			int score = child.minimax(depth + 1).val;
			return new Best(score, move);
		});

		return this.computerPlays()
			? moves.reduce(new Best(Integer.MIN_VALUE), (a, b) -> a.val > b.val ? a : b)
			: moves.reduce(new Best(Integer.MAX_VALUE), (a, b) -> a.val < b.val ? a : b);
	}

	/**
	 * Rate this board.
	 *
	 * @param depth How many moves in the future we are.
	 */
	private int score(int depth) {
		if (this.isAWin(HUMAN)) {
			// This is a loss for the AI--so it's best to defer that as long as
			// possible. A loss further away gets a better score than a loss in the
			// next turn.
			return depth - 9;
		} else if (this.isAWin(COMPUTER)) {
			// This is a win for the AI--so it's best to get there ASAP. A win sooner
			// gets a better score than a win later.
			return 9 - depth;
		}
		return 0;
	}

	/**
	 * Get available moves (empty squares).
	 *
	 * @return All available moves.
	 */
	private Collection<Integer> availableMoves() {
		Collection<Integer> moves = new ArrayList<>(9);
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (this.squareIsEmpty(row, col)) {
					moves.add(row * 3 + col);
				}
			}
		}
		return moves;
	}

	//check if move ok
	public boolean moveOk(int move) {
		return move >= 0 &&
			move <= 8 &&
			this.squareIsEmpty(move / 3, move % 3);
	}

	// play move
	public TicTacToe playMove(int move) {
		board[move / 3][move % 3] = this.side;
		if (side == COMPUTER) {
			this.side = HUMAN;
		} else {
			this.side = COMPUTER;
		}
		return this;
	}

	// Simple supporting routines
	private void clearBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				this.board[row][col] = EMPTY;
			}
		}
	}

	private boolean boardIsFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (this.board[row][col] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Create a stream of all winning positions, i.e. series of locations that
	 * when occupied by a single player count as a win for that player.
	 *
	 * @return Streeeeeeeeeeeeeeeeeeeams.
	 */
	private Stream<IntStream> winningPositions() {
		return Stream.of(
			// Horizontals
			IntStream.of(0, 1, 2),
			IntStream.of(3, 4, 5),
			IntStream.of(6, 7, 8),
			// Verticals
			IntStream.of(0, 3, 6),
			IntStream.of(1, 4, 7),
			IntStream.of(2, 5, 8),
			// Diagonals
			IntStream.of(0, 4, 8),
			IntStream.of(2, 4, 6)
		);
	}

	// Returns whether 'side' has won in this position
	private boolean isAWin(int side) {
		return this.winningPositions().anyMatch(line ->
			line
				.map(position -> this.board[position / 3][position % 3])
				.allMatch(field -> field == side)
		);
	}

	// Play a move, possibly clearing a square
	private void place(int row, int column, int piece) {
		board[row][column] = piece;
	}

	private boolean squareIsEmpty(int row, int column) {
		return this.board[row][column] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue() {
		if (this.isAWin(HUMAN)) {
			return HUMAN_WIN;
		} else if (this.isAWin(COMPUTER)) {
			return COMPUTER_WIN;
		} else if (this.boardIsFull()) {
			return DRAW;
		}
		return UNCLEAR;
	}

	public String toString() {
		StringBuilder render = new StringBuilder();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int value = this.board[row][col];
				if (value == HUMAN) {
					render.append(this.humanChar);
				} else if (value == COMPUTER) {
					render.append(this.computerChar);
				} else {
					render.append('.');
				}
			}
			render.append('\n');
		}
		return render + "";
	}

	public boolean gameOver() {
		this.position = positionValue();
		return this.position != UNCLEAR;
	}

	public String winner() {
		if (this.position == COMPUTER_WIN) {
			return "computer";
		} else if (this.position == HUMAN_WIN) {
			return "human";
		} else {
			return "nobody";
		}
	}

	public TicTacToe clone() {
		return new TicTacToe(this.board, this.side);
	}

	private class Best {
		int row;
		int column;
		int val;

		public Best(int v) {
			this(v, 0, 0);
		}

		public Best(int v, int move) {
			this(v, move / 3, move % 3);
		}

		public Best(int v, int r, int c) {
			val = v;
			row = r;
			column = c;
		}
	}
}
