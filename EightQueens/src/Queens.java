import java.util.Scanner;

public class Queens {

	// A variable to hold a user selected column
	int usedColumn;
	
	// A variable which holds the number of solutions
	int numberOfSolution = 1;

	// Creating a String chess board
	String[][] chessBoard = new String[8][8];

	
	
	
	// This method is used to find all the positions on the board 
	//  	with empty spaces to allow queens to be placed
	public void Setup() {

		for (int row = 0; row < chessBoard.length; row++) {
			for (int column = 0; column < chessBoard[row].length; column++) {

				// Looking for all the positions with spaces
				chessBoard[row][column] = " ";
			}
		}
	}

	
	

	// This method is used to print the current board
	public void Board() {

		// Starting point of the rows in the chess board
		int numPosition = 1;

		// Print out the columns of the chess board
		System.out.print("\n    a   b   c   d   e   f   g   h");

		
		// For loop been used to create the chess board
		for (int row = 0; row < chessBoard.length; row++) {
			
			// Print out the rows of the chess board
			System.out.print("\n  +-------------------------------+\n" + numPosition++ + " |");

			for (int column = 0; column < chessBoard[row].length; column++) {
				System.out.print(" ");
				System.out.print(chessBoard[row][column]);
				// Will print out the empty space or a queen
				System.out.print(" |");
				
				
			}
		}
		System.out.print("\n  +-------------------------------+\n");
	}

	
	

	// This method is used to place the queen in a position
	//		which was selected by a user
	public void firstQueen() {
		// Get an input from a user
		Scanner userInput = new Scanner(System.in);
		
		// This variable is used to store a user input
		String boardPosition;
		
		boolean success = false;

		System.out.print("Please select a position on the board (e.g. a1): ");
		boardPosition = userInput.next();


		// If user input does not meet the input requirement 
		//	   such a1,b6 or others. It must be 1 lower case letter and a number
		// Those user input will be blocked and will ask again for a user input
		while (!success) {
			try {
				int column = boardPosition.charAt(0) - 'a';
				int row = boardPosition.charAt(1) - 49; 		//1 index means 2 position of the row
				chessBoard[row][column] = "\u2655 "; 			//Enter user's Queen at
																//	 specified position
								
				usedColumn = column;
				success = true;
			}

			catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("Please enter a valid two character position between a1 and h8: ");
				boardPosition = userInput.next();
			}

			catch (StringIndexOutOfBoundsException e) {
				System.out.print("Please enter a valid two character position between a1 and h8: ");
				boardPosition = userInput.next();
			}

			if (boardPosition.length() != 2) {
				System.out.print("Please enter a valid two character position between a1 and h8: ");
				boardPosition = userInput.next();
			}

		}
		// Close the user input
		userInput.close();
	}

	
	
	
	// This method is used to check if queen can be safely placed in the column
	// It checks number of possibilities such as in the same row,
	//		same upper and lower diagonal.
	public boolean isSafe(String chessBoard[][], int row, int column) {

		int i, j;

		// Checking if queen is placed in the same row
		for (j = 0; j < 8; j++) {
			if (chessBoard[row][j] == "\u2655 ")
				return false;
		}

		// Check if queen is placed in upper diagonal on the left
		for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
			if (chessBoard[i][j] == "\u2655 ")
				return false;
		}

		// Check if queen is placed in lower diagonal on the left
		for (i = row, j = column; i < 8 && j >= 0; i++, j--) {
			if (chessBoard[i][j] == "\u2655 ")
				return false;
		}

		// Check if queen is placed in upper diagonal on the right
		for (i = row, j = column; i >= 0 && j < 8; i--, j++) {
			if (chessBoard[i][j] == "\u2655 ")
				return false;
		}
		
		// Check if queen is placed in lower diagonal on the right
		for (i = row, j = column; i < 8 && j < 8; i++, j++) {
			if (chessBoard[i][j] == "\u2655 ")
				return false;
		}
		return true;
	}

	
	
	// This method is used to start placing the queens 
	// 		by calling isSafe() method using backtracking way
	public boolean placeQueens(String chessBoard[][], int column) {


		// If all queens been placed, print out all the solution
		// 		which include the user input's position
		if (column == 8) {
			
			System.out.print("\nSolution " + numberOfSolution++);
			Board();
			
			return false;
		}

		// If the column holds a queen from user input, skip it
		if (column == usedColumn) {
			if (placeQueens(chessBoard, column + 1) == true)
				return true;
		}
		
		for (int i = 0; i < 8; i++) {


			// If isSafe() returns true, place a queen in the position
			//		and move to the next column
			if (isSafe(chessBoard, i, column)) {
				chessBoard[i][column] = "\u2655 ";

				if (placeQueens(chessBoard, column + 1) == true)
					return true;

				// Else, backtrack it.
				// 		do not place the queen
				// 		remove a queen from the previous column
				// 		place it in different row of the same column
				else 
					chessBoard[i][column] = " ";
			}			
		}
		return false;
	}

	
	
	// This method is used to initialise the first column
	// 		to place the queens. 
	// This method can be easily called in the Main class
	public void solveQueens() {
		placeQueens(chessBoard, 0);
	}
	
}