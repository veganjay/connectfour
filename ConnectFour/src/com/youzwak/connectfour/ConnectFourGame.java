package com.youzwak.connectfour;

import com.youzwak.connectfour.ConnectFourBoard.ColumnFullException;
import com.youzwak.connectfour.ConnectFourBoard.OutOfRangeException;
import com.youzwak.connectfour.ConnectFourBoard.Piece;

import com.youzwak.game.*;

public class ConnectFourGame extends Game {
	
	public static final Piece START_PLAYER = Piece.BLACK;
	
	// Member variables
	private ConnectFourBoard board;
	private Piece humanPiece;
	private Piece computerPiece;
	private ConnectFourAI ai;
		
	public ConnectFourGame() {
		super();
		this.board= new ConnectFourBoard();
	}	
	
	private int getChoice() {
		String choice;
		int    col;
		
		// Display the choice
		System.out.print("Which column? ");
		
		// Read from input
		choice = super.getInput();
		System.out.println();
		
		try {
			col = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("Enter the column number (0-7)");
			return getChoice();
		}
		return col;
	}

	/*
	public void mainLoop() {
		// Initialize with start player
		Piece p = START_PLAYER;

		// Display the board;
		board.printBoard();
		
		while (true) {		
			try {
				// Get the choice
				int col = getChoice();
				
				// Add the piece to the board
				board.addToColumn(col, p);
				
				// Display the board;
				board.printBoard();
	
				// Check for winner
				if (board.isWinner(p)) {
					System.out.println(p + " is the winner!");
					break;
				}
				
				// Switch pieces
				if (p == Piece.BLACK) {
					p = Piece.RED;
				} else {
					p = Piece.BLACK;
				}	
			} catch (OutOfRangeException e) {
				System.out.println("Column out of range.");
			} catch (ColumnFullException e) {
				System.out.println("Column is full.");
			}
		} // End while

	} // End main loop
	*/
	
	public static void main(String [] args) {
		ConnectFourGame game = new ConnectFourGame();
		
System.out.println("Welcome to Connect Four!");
		
		game.mainLoop();
	} // End main

	@Override
	protected void initialize() {
		board.reset();
		if (getStartPlayer() == Player.HUMAN) {
			humanPiece    = Piece.BLACK;
			computerPiece = Piece.RED;
		} else {
			humanPiece    = Piece.RED;
			computerPiece = Piece.BLACK;
		}
		ai = new ConnectFourAI(computerPiece);
		board.printBoard();
	}

	@Override
	protected boolean isFinished() {
		boolean done = false;
		
		if (isComputerWinner() || isHumanWinner()) {
			done = true;
		} else if (board.isFull()) {
			done = true;
		}
		return done;
	}

	@Override
	protected boolean isComputerWinner() {
		return board.isWinner(computerPiece);
	}

	@Override
	protected boolean isHumanWinner() {
		return board.isWinner(humanPiece);
	}

	@Override
	protected void playHumanTurn() {
		// Get the choice
		int col = getChoice();
		
		// Add the piece to the board
		try {
			board.addToColumn(col, humanPiece);
		} catch (OutOfRangeException e) {
			System.out.println("column out of range");
			playHumanTurn();
		} catch (ColumnFullException e) {
			System.out.println("column is full");
			playHumanTurn();
		}
		
		// Display the board
		board.printBoard();
	}

	@Override
	protected void playComputerTurn() {	
		// Get the choice
		int col = ai.getMove(board);
		
		// Add the piece to the board
		try {
			board.addToColumn(col, computerPiece);
		} catch (OutOfRangeException e) {
			System.out.println("column out of range");
			playComputerTurn();
		} catch (ColumnFullException e) {
			System.out.println("column is full");
			playComputerTurn();
		}
		
		System.out.println("Computer moves in column " + col);
		
		// Display the board
		board.printBoard();		
	}

}
