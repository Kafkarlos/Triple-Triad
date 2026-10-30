package principal.model;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import principal.model.enums.Color;

public class Board {

	private static final int INNER_WIDTH = 11;
	private static final int CARD_LINES  = 5;
	
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
		
		if(cardTop != null && !cardTop.getOwner().equals(playedCard.getOwner())) {
			boolean result = battle(playedCard, cardTop, "UP");
			startCombo(result, rowTop, colTop, playedCard.getOwner());
		}
		if(cardLeft != null && !cardLeft.getOwner().equals(playedCard.getOwner())) {
			boolean result = battle(playedCard, cardLeft, "LEFT");
			startCombo(result, rowLeft, colLeft, playedCard.getOwner());
		}
		if(cardRight != null && !cardRight.getOwner().equals(playedCard.getOwner())) {
			boolean result = battle(playedCard, cardRight, "RIGHT");
			startCombo(result, rowRigth, colRight, playedCard.getOwner());
		}
		if(cardDown != null && !cardDown.getOwner().equals(playedCard.getOwner())) {
			boolean result = battle(playedCard, cardDown, "DOWN");
			startCombo(result, rowDown, colDown, playedCard.getOwner());
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
	
	public void startCombo(boolean result, int row, int col, Player currentPlayer) {
		if(result) {
			compare(row, col);
		}
	}
	
	public boolean checkPositionOccupied(int row, int col) {
		return grid[row][col] != null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < grid.length; row++) {
			String[][] cards = new String[grid[row].length][];
			for (int col = 0; col < grid[row].length; col++) {
				Card card = grid[row][col];
				cards[col] = (card == null) ? Card.emptyLines() : card.toLines();
			}
			for (int line = 0; line < Card.HEIGHT; line++) {
				for (int col = 0; col < cards.length; col++) {
					sb.append(cards[col][line]);
					if (col < cards.length - 1) {
						sb.append(" ");
					}
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
