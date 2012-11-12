package com.youzwak.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Game {

	public enum Player { HUMAN, COMPUTER };
		
	// Member variables
	private BufferedReader input;
	private Player currentPlayer;
	private Player startPlayer;
	
	/*
	 * Initialize the input reader
	 */
	public Game() {
		this.input= new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * @return a line of input from the user
	 */
	protected String getInput() {
		String line = "";
		try {
			return this.input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;		
	}

	/**
	 * Ask the user if she wants to go first
	 */
	protected void askStartPlayer() {
		System.out.print("Do you want to go first? [y] ");
		
		String choice = this.getInput();

		if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
			startPlayer = Player.COMPUTER;
		} else {
			startPlayer = Player.HUMAN;
		}
		
		currentPlayer = startPlayer;
	}
	
	/**
	 * @return the current player
	 */
	protected Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * @return the start player
	 */
	protected Player getStartPlayer() {
		return startPlayer;
	}
	
	private void switchPlayers() {
		if (currentPlayer == Player.COMPUTER) {
			currentPlayer = Player.HUMAN;
		} else {
			currentPlayer = Player.COMPUTER;
		}
	}
	
	/**
	 * Ask the user if she wants to play again
	 * @return true if playing again, false if quitting
	 */
	protected boolean askPlayAgain() {
		boolean playAgain = true;
		System.out.print("Do you want to play again? [y] ");
		String choice = this.getInput();
		if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
			playAgain = false;
		}
		return playAgain;
	}		

	/**
	 * Display the winner, or tie game.
	 */
	protected void displayWinner() {
		if (isComputerWinner()) {
			System.out.println("Computer wins!");
		} else if (isHumanWinner()) {
			System.out.println("You win!");
		} else {
			System.out.println("Tie Game!");
		}
	}
	
	/**
	 * Run the main game loop:
	 * - Initialize the board
	 * - Ask for start player
	 * - Run the game
	 * - Ask if the user wants to play again
	 */
	public void mainLoop() {		
		do {
			askStartPlayer();			
			initialize();

			// Keep playing until game is finished
			while (!isFinished()) {				
				if (currentPlayer.equals(Player.HUMAN)) {
					playHumanTurn();
				} else {
					playComputerTurn();
				}				
				switchPlayers();
			}
			// Display the winner
			displayWinner();
		} while (askPlayAgain());
	}
	
	// Abstract methods

	/**
	 * Initialize the game.
	 * The game board should be cleared
	 */
	protected abstract void initialize();
	
	/**
	 * @return true if the game is finished
	 */
	protected abstract boolean isFinished();
	
	/**
	 * @return true if computer wins
	 */
	protected abstract boolean isComputerWinner();
	
	/**
	 * @return true if human wins
	 */
	protected abstract boolean isHumanWinner();

	/**
	 * Play a human turn in the game.
	 */	
	protected abstract void playHumanTurn();
	protected abstract void playComputerTurn();
	
} // End class Game
