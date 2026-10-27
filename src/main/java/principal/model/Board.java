package principal.model;

public class Board {

	Card[][] grid;
	
	public Board(int row,int col) {
		grid = new Card[3][3];
	}
	
	public void addCard(Card card,int row,int col) {
		grid[row][col] = card;
	}
	
	public void compare(int row, int col) {
		int rowTop = row - 1,colTop = col;
		int rowLeft = row, colLeft = col - 1;
		int rowRigth = row, colRight = col + 1;
		int rowDown = row + 1,colDown = col;
		
		Card playedCard =  grid[row][col];
		Card cardTop = verifyCardPosistionInBounds(rowTop, colTop);
		Card cardLeft = verifyCardPosistionInBounds(rowLeft, colLeft);
		Card cardRight = verifyCardPosistionInBounds(rowRigth, colRight);
		Card cardDown = verifyCardPosistionInBounds(rowDown, colDown);
		
		if(cardTop != null) {
			boolean result = battle(playedCard, cardTop, "UP");
			startCombo(result, rowTop, colTop);
		}
		if(cardLeft != null) {
			boolean result = battle(playedCard, cardLeft, "LEFT");
			startCombo(result, rowLeft, colLeft);
		}
		if(cardRight != null) {
			boolean result = battle(playedCard, cardRight, "RIGHT");
			startCombo(result, rowRigth, colRight);
		}
		if(cardDown != null) {
			boolean result = battle(playedCard, cardDown, "DOWN");
			startCombo(result, rowDown, colDown);
		}
	}
	
	public boolean battle(Card playedCard, Card adversaryCard, String rank) {
		if(playedCard.compareValueOf(rank, adversaryCard)) {
			transform(adversaryCard, playedCard.getOwner());
			return true;
		}
		
		return false;
	}
	
	public Card verifyCardPosistionInBounds(int row, int col) {
		try {
			return grid[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void transform(Card oponent,Player owner) {
		oponent.getOwner().setPoints(oponent.getOwner().getPoints()-1);
		oponent.setOwner(owner);
		owner.setPoints(owner.getPoints()+1);
	}
	
	public void startCombo(boolean result, int row, int col) {
		if(result) {
			compare(row, col);
		}
	}
	
	public boolean checkPositionOccupied(int row, int col) {
		return grid[row][col] != null;
	}
	
}
