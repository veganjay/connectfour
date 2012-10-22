package com.youzwak.connectfour;

import com.youzwak.connectfour.ConnectFourBoard.*;
import com.youzwak.connectfour.ConnectFourBoard.Piece;
import java.util.Scanner;

public class ConnectFourGame {
	
	public static final Piece START_PLAYER = Piece.BLACK;
	
	// Member variables
	private Scanner input;
	private ConnectFourBoard board;
	
	public ConnectFourGame() {
		this.input=new Scanner(System.in);		
		this.board= new ConnectFourBoard();
	}
	
	private int getChoice() {
		String choice;
		int    col;
		
		// Display the choice
		System.out.print("Which column? ");
		
		// Read from input
		choice = input.next();
		System.out.println();
		
		try {
			col = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("Enter the column number (0-7)");
			return getChoice();
		}
		return col;
	}

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
	
	public static void main(String [] args) {
		ConnectFourGame game = new ConnectFourGame();
		
		game.mainLoop();
	} // End main

}
