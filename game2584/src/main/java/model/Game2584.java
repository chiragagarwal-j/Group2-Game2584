package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game2584 {
	public Tile[][] board;
	int grids = 4;
	int border = 0;
	public int score = 0;
	boolean flag = true;

	public Game2584() {
		board = new Tile[4][4];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Tile();
			}
		}
		spawn();
		spawn();
	}

	public Tile[][] getBoard() {
		return board;
	}

	public int[][] getIntBoard() {
		int[][] intBoard = new int[4][4];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				intBoard[i][j] = board[i][j].getValue();
			}
		}
		return intBoard;
	}

	public int getScore() {
		return score;
	}

	/**
	 * 
	 * Finds the the highest tile on the board and returns it
	 * 
	 * @return high
	 */
	public int getHighTile() {
		int high = board[0][0].getValue();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getValue() > high) {
					high = board[i][j].getValue();
				}
			}
		}
		return high;
	}

	/**
	 * 
	 * Prints out the board on the console - for testing purposes
	 */
	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				String s = board[i][j].toString() + " ";
				System.out.print(s);
			}
			System.out.println("");
		}
		System.out.println("Score: " + score);
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				s += board[i][j].toString() + " ";
			}
			s += "\n";
		}
		return s;
	}

	public String[] toSingleArray() {
		String[] arr = new String[16];
		int index = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int val = board[i][j].getValue();
				if (val == 0) {
					arr[index] = "";
				} else {
					arr[index] = String.valueOf(val);
				}
				index++;
			}
		}
		return arr;
	}

	public void spawn() {
		boolean empty = true;
		while (empty) {
			int row = (int) (Math.random() * 4);
			int col = (int) (Math.random() * 4);
			if (board[row][col].getValue() == 0) {

				board[row][col] = new Tile(1);
				empty = false;

			}

		}

	}

	/**
	 * 
	 * Checks to see if the board is completely blacked out and if it is, it will
	 * give a suggestion to the player - nudging them to restart
	 * 
	 * @return boolean
	 */
	public boolean blackOut() {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getValue() > 0) {
					count++;
				}
			}
		}
		if (count == 16) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Checks to see if the game is over - that is, checks if any tile (that isn't a
	 * 0) is able to combine with the tiles next to it - If not, the game is over
	 * 
	 * @return boolean
	 */
	public boolean gameOver() {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getValue() > 0) {
					if (i == 0 && j == 0) {
						if (board[i][j].getValue() != board[i + 1][j].getValue()
								&& board[i][j].getValue() != board[i][j + 1].getValue()) {
							count++;
						}
					} else if (i == 0 && j == 3) {
						if (board[i][j].getValue() != board[i + 1][j].getValue()
								&& board[i][j].getValue() != board[i][j - 1].getValue()) {
							count++;
						}
					} else if (i == 3 && j == 3) {
						if (board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i][j - 1].getValue()) {
							count++;
						}
					} else if (i == 3 && j == 0) {
						if (board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i][j + 1].getValue()) {
							count++;
						}
					} else if (i == 0 && (j == 1 || j == 2)) {
						if (board[i][j].getValue() != board[i + 1][j].getValue()
								&& board[i][j].getValue() != board[i][j + 1].getValue()
								&& board[i][j].getValue() != board[i][j - 1].getValue()) {
							count++;
						}
					} else if (i == 3 && (j == 1 || j == 2)) {
						if (board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i][j + 1].getValue()
								&& board[i][j].getValue() != board[i][j - 1].getValue()) {
							count++;
						}
					} else if (j == 0 && (i == 1 || i == 2)) {
						if (board[i][j].getValue() != board[i][j + 1].getValue()
								&& board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i + 1][j].getValue()) {
							count++;
						}
					} else if (j == 3 && (i == 1 || i == 2)) {
						if (board[i][j].getValue() != board[i][j - 1].getValue()
								&& board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i + 1][j].getValue()) {
							count++;
						}
					} else {
						if (board[i][j].getValue() != board[i][j - 1].getValue()
								&& board[i][j].getValue() != board[i][j + 1].getValue()
								&& board[i][j].getValue() != board[i - 1][j].getValue()
								&& board[i][j].getValue() != board[i + 1][j].getValue()) {
							count++;
						}
					}
				}
			}
		}
		if (count == 16) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * This method is called when a 'w' or up arrow is pressed - goes through the
	 * entire board and calls verticalMove with an "up" parameter for each tile
	 */
	public void up() {
		for (int i = 0; i < grids; i++) {
			border = 0;
			for (int j = 0; j < grids; j++) {
				if (board[j][i].getValue() != 0) {
					if (border <= j) {
						verticalMove(j, i, "up");
					}
				}
			}
		}
	}

	/**
	 * 
	 * This method is called when a 's' or down arrow is pressed - goes through the
	 * entire board and calls verticalMove with a "down" parameter for each tile
	 */
	public void down() {
		for (int i = 0; i < grids; i++) {
			border = (grids - 1);
			for (int j = grids - 1; j >= 0; j--) {
				if (board[j][i].getValue() != 0) {
					if (border >= j) {
						verticalMove(j, i, "down");
					}
				}
			}
		}
	}

	/**
	 * 
	 * Compares two tile's values together and if they are the same or if one is
	 * equal to 0 (plain tile) - their values are added (provided that the tiles we
	 * are comparing are two different tiles and they are moving towards the
	 * appropriate direction) - Uses recursion to go through the entire column
	 * 
	 * @param row       row that the compare tile is currently on
	 * @param col       column that the compare tile is currently on
	 * @param direction direction (up or down) that the tile is moving in
	 */

	private void verticalMove(int row, int col, String direction) {

		System.out.println("vertcal: " + border + direction);

		Tile initial = board[this.border][col];
		Tile compare = board[row][col];
		if (initial.getValue() == 0
				|| (nextFib(initial.getValue()) == compare.getValue()
						|| initial.getValue() == nextFib(compare.getValue()))
				|| ((initial.getValue() == 1 && compare.getValue() == 1)
						|| (initial.getValue() == 1 && compare.getValue() == 2)
						|| (initial.getValue() == 2 && compare.getValue() == 1))) {
			if (row > this.border || (direction.equals("down") && (row < this.border))
					|| (direction.equals("up") && (row > this.border))) {
				int addScore = initial.getValue() + compare.getValue();
				if (initial.getValue() != 0) {
					score += addScore;
				}
				initial.setValue(addScore);
				compare.setValue(0);
			} else {
				flag = false;
			}
		} else {
			if (direction.equals("down")) {

				if (border <= 3 && border >= 1) {
					border = border - 1;
					horizontalMove(row, col, direction);
				}

			}
			if (direction.equals("up")) {

				if (border <= 2 && border >= 0) {
					border = border + 1;
					horizontalMove(row, col, direction);
				}

			}
		}
	}

	public boolean getFlag() {
		return flag;
	}

	/**
	 * 
	 * This method is called when an 'a' or left arrow is pressed - goes through the
	 * entire board and calls horizontalMove with a "left" parameter for each tile
	 */
	public void left() {
		for (int i = 0; i < grids; i++) {
			border = 0;
			for (int j = 0; j < grids; j++) {
				if (board[i][j].getValue() != 0) {
					if (border <= j) {
						horizontalMove(i, j, "left");
					}
				}
			}
		}
	}

	/**
	 * 
	 * This method is called when a 'd' or right arrow is pressed - goes through the
	 * entire board and calls horizontalMove with a "right" parameter for each tile
	 */
	public void right() {
		for (int i = 0; i < grids; i++) {
			border = (grids - 1);
			for (int j = (grids - 1); j >= 0; j--) {
				if (board[i][j].getValue() != 0) {
					if (border >= j) {
						horizontalMove(i, j, "right");
					}
				}
			}
		}
	}

	/**
	 * 
	 * Compares two tile's values together and if they are the same or if one is
	 * equal to 0 (plain tile) - their values are added (provided that the tiles we
	 * are comparing are two different tiles and they are moving towards the
	 * appropriate direction) - Uses recursion to go through the entire row
	 * 
	 * @param row       row that the compare tile is currently on
	 * @param col       column that the compare tile is currently on
	 * @param direction direction (left or right) that the tile is moving in
	 */
	private void horizontalMove(int row, int col, String direction) {
		System.out.println("horizontal: " + border + direction);
		Tile initial = board[row][this.border];
		Tile compare = board[row][col];
		if (initial.getValue() == 0
				|| (nextFib(initial.getValue()) == compare.getValue()
						|| initial.getValue() == nextFib(compare.getValue()))
				|| ((initial.getValue() == 1 && compare.getValue() == 1)
						|| (initial.getValue() == 1 && compare.getValue() == 2)
						|| (initial.getValue() == 2 && compare.getValue() == 1))) {
			if (col > this.border || (direction.equals("right") && (col < this.border))
					|| (direction.equals("left") && (col > this.border))) {
				int addScore = initial.getValue() + compare.getValue();
				if (initial.getValue() != 0) {
					score += addScore;
				}
				initial.setValue(addScore);
				compare.setValue(0);
			}

			else {
				flag = false;
			}
		} else {
			if (direction.equals("right")) {

				if (border <= 3 && border >= 1) {
					border = border - 1;
					horizontalMove(row, col, direction);
				}

			}
			if (direction.equals("left")) {

				if (border <= 2 && border >= 0) {
					border = border + 1;
					horizontalMove(row, col, direction);
				}

			}

		}

	}

	public int nextFib(int n) {
		List<Integer> l = new ArrayList<>();
		l = Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597);
		int index = l.indexOf(n);
		return (l.get(index + 1));

	}

}
