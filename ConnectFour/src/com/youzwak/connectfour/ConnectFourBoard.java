package com.youzwak.connectfour;

public class ConnectFourBoard {

	public enum Piece { EMPTY, RED, BLACK };
	
	// Constants
	public static final int COLUMNS = 7;
	public static final int ROWS    = 10;
	
	// Board
	private Piece board[][];
	
	public ConnectFourBoard() {
		this.board = new Piece[COLUMNS][ROWS];
		this.initialize();
	}
	
	private void initialize() {
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				this.board[i][j] = Piece.EMPTY;
			}
		}
	}
	
	public void addToColumn(int col, Piece p) throws OutOfRangeException, ColumnFullException {
		if (col < 0 || col > COLUMNS)
			throw new OutOfRangeException();
		
		Piece column[] = this.board[col];
		int   top = getTopOfColumn(col);
		
		if (top >= ROWS)
			throw new ColumnFullException();
		column[top++] = p;		
	}
	
	public int getTopOfColumn(int col) {
		int top = 0;

		while(top < ROWS && board[col][top] != null) {
			top++;
		}
		
		return top;
	}
	
	public void printBoard() {
		for (int j = ROWS - 1; j >= 0; j--) {
			for (int i = 0; i < COLUMNS; i++) {
				if (board[i][j] == null) {
					System.out.print(". ");
				} else if (board[i][j] == Piece.RED) {
					System.out.print("R ");
				} else if (board[i][j] == Piece.BLACK) {
					System.out.print("B ");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < COLUMNS; i++) {
			System.out.print(i + " ");			
		}
		System.out.println();
		
	}
	
	public boolean isWinner(Piece p) {
		boolean won = false;
		
		return won;
	}
	
	public boolean hasHorizontalWin(Piece p) {
		boolean won = false;

		for (int i = 0; i < ROWS - 4; i++) {
		}
		
		return won;
	}
	
	public class OutOfRangeException extends Exception {
		
	}
	
	public class ColumnFullException extends Exception {
		
	}

}
