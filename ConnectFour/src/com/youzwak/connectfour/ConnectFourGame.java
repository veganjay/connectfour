package com.youzwak.connectfour;

import com.youzwak.connectfour.ConnectFourBoard.*;
import com.youzwak.connectfour.ConnectFourBoard.Piece;

public class ConnectFourGame {
	public static void main(String [] args) {
		ConnectFourBoard board = new ConnectFourBoard();
		
		try {
			board.addToColumn(0, Piece.BLACK);
			board.addToColumn(1, Piece.RED);
			board.addToColumn(1, Piece.RED);
			board.addToColumn(1, Piece.RED);
			board.addToColumn(1, Piece.RED);

		} catch (OutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ColumnFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		board.printBoard();
	}
}
