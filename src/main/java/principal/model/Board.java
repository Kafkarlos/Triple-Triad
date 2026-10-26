package principal.model;

public class Board {

	Card[][] grid;
	
	public Board(int row,int col) {
		grid = new Card[3][3];
	}
	
	public boolean addCard(Card card, int row, int col) {
		if(checkPositionOccupied(row,col)) {
			return false;
		}
		grid[row][col] = card;
		return true;
	}
	
	public void compare(int row, int col) {
		int rowTop = row - 1,colTop = col;
		int rowLeft = row, colLeft = col - 1;
		int rowRigth = row, colRight = col + 1;
		int rowDown = row + 1,colDown = col;
		
		try {
			Card cardTop = grid[rowTop][colTop];
			Card cardLeft = grid[rowLeft][colLeft];
			Card cardRight = grid[rowRigth][colRight];
			Card cardDown = grid[rowDown][colDown];
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
	
		
		
	}
	
	public boolean transform(Card oponent,Player owner) {
		return true;
	}
	
	public void startCombt() {
		
	}
	
	public boolean checkPositionOccupied(int row, int col) {
		return grid[row][col] != null;
	}
	
}
