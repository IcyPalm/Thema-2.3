package cards;
import java.util.Stack;
/** the solution is a sequence of cards placed on the board according to the card positions
    example without border
*/
public class Solution extends Stack<Candidate>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8556591170740305342L;

	// The board is an 2D array.
	// 0123
	// 0..-.
	// 1---.
	// 2.---
	// 3..-.
	private Candidate[][] board = new Candidate[4][4];
	
	// card positions on the board
	// the first card position on the board are
	// {0,2}, {1,0}. {1,1}
	private int[] row    = { 0, 1, 1, 1, 2, 2, 2, 3 };
	private int[] column = { 2, 0, 1, 2, 1, 2, 3, 2 };
	//  indices of adjacent cards in the solution.
	//                 0   1  2   3   4    5     6    7   
	int [] [] check = {{},{},{1},{0},{2},{3,4},{5,6},{7}}; 
	
	
	public Solution(){
	}

	
	 // Checks whether a candidate with card CardChar is in 
	 // an adjacent position of the board position (row, column)
	 // @param row, column, candidate
	 // @return Boolean indicating if cardChar is found.
	 // can be used in the methods fits and isCorrect
	private boolean bordersCard(int row, int column, char cardChar){
	    
		//row-1
		if(row>0){
			Candidate borderingCard = board[row-1][column];
			if(borderingCard!=null&& borderingCard.getCardChar() == cardChar){
				return true;
			}
		}
		//row+1
		if(row<board.length-1){
			Candidate borderingCard = board[row+1][column];
			if(borderingCard!=null&& borderingCard.getCardChar() == cardChar){
				return true;
			}
		}
		//col-1
		if(column>0){
			Candidate borderingCard = board[row][column-1];
			if(borderingCard!=null&& borderingCard.getCardChar() == cardChar){
				return true;
			}
		}
		//col+1
		if(column<board[0].length-1){
			Candidate borderingCard = board[row][column+1];
			if(borderingCard!=null&& borderingCard.getCardChar() == cardChar){
				return true;
			}
		}
		return false;
    }
	
	
	/**
	 * Checks whether candidate card of same kind. Checks whether by placing
	 * candidate the solution sofar still complies with the rules
	 * 
	 * @param candidate
	 * @return boolean indicating whether this candidate can be put in the next
	 *         free position.
	 */
	public boolean fits(Candidate candidate) {
		// check if 
		if(bordersCard(row[this.size()], column[this.size()], candidate.getCardChar())){
			return false;
		}
		return isCorrect(candidate);
	}

	public void record(Candidate candidate) {
		int i = this.size(); // i= index in this stack of next for the next
								// candidate
		board[row[i]][column[i]] = candidate; // x=row, y=column
		this.push(candidate);

	}

	public boolean complete() {
		return this.size() == 8;
	}

	public void show() {
		System.out.println(this);
	}

	public Candidate eraseRecording() {
		int i = this.size() - 1; // i= index of the candidate that is removed
									// from this Stack;
		board[row[i]][column[i]] = null; // remove candidate from board
		return this.pop();
	}

	// can be used in method isCorrect
	private char mustBeAdjacentTo(char card) {
		if (card == 'A')
			return 'K';
		if (card == 'K')
			return 'Q';
		if (card == 'Q')
			return 'J';
		return '?'; // error
	}

	/**
	 * Checks whether the rules below are fulfilled For the positions that can
	 * be checked for solution sofar. 
	 * Rules: 
	 * 		Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king). 
	 * 		Elke heer grenst aan een vrouw(queen). 
	 * 		Elke vrouw grenst aan een boer (jack).
	 * 
	 * @return true if all checks are correct.
	 */
	// uses methods borderCard and mustBeAdjacent to
	private boolean isCorrect(Candidate candidate) {
		if (this.size() != 7)	return true;
		for (int i = 0; i < this.size(); i++) {
			char cardChar = this.get(i).getCardChar();
			if (cardChar != 'J') {
				if (! bordersCard(row[i], column[i], mustBeAdjacentTo(cardChar))) {
					return false;
				}
		
			}
		}
		char candidateChar = candidate.getCardChar();
		if (candidateChar != 'J') {
			if (! bordersCard(row[this.size()], column[this.size()], mustBeAdjacentTo(candidateChar))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return a representation of the solution on the board
	 */
	public String toString() {
		String output = "";
		
		for (int i = 0; i < board.length; i++) {
			String row = "";
			for (int j = 0; j < board[i].length; j++) {
				Candidate finalist = board[i][j];
				if (finalist != null) {
					row += "|" + finalist.getCardChar();
					if (j == board[0].length - 1 || board[i][j + 1] == null) {
						row += "|";
					}
				} else {
					row += "  ";
				}
			}
			output += row + "\n";
		}

		return output;
	}

}
