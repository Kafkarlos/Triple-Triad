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
	
	@Override
	public String toString() {
		AttributedStringBuilder sb = new AttributedStringBuilder();

		for (int row = 0; row < grid.length; row++) {
			for (int line = 0; line < CARD_LINES; line++) {
				for (int col = 0; col < grid[row].length; col++) {
					appendCardLine(sb, grid[row][col], line);
					if (col < grid[row].length - 1) {
						sb.append(" ");
					}
				}
				sb.append("\n");
			}
		}
		return sb.toAnsi();
	}

	private void appendCardLine(AttributedStringBuilder sb, Card card, int line) {
		AttributedStyle style = (card == null)
				? AttributedStyle.DEFAULT.faint()
				: styleOf(card.getOwner());

		String text;
		switch (line) {
			case 0:
				text = "┌" + repeat("─", INNER_WIDTH) + "┐";
				break;
			case 1:
				text = "│" + center(card == null ? "" : rank(card, "UP"), INNER_WIDTH) + "│";
				break;
			case 2:
				if (card == null) {
					text = "│" + repeat(" ", INNER_WIDTH) + "│";
				} else {
					String name = card.getName() == null ? "" : card.getName();
					if (name.length() > INNER_WIDTH - 2) {
						name = name.substring(0, INNER_WIDTH - 2);
					}
					text = "│" + rank(card, "LEFT")
							+ center(name, INNER_WIDTH - 2)
							+ rank(card, "RIGHT") + "│";
				}
				break;
			case 3:
				text = "│" + center(card == null ? "" : rank(card, "DOWN"), INNER_WIDTH) + "│";
				break;
			default:
				text = "└" + repeat("─", INNER_WIDTH) + "┘";
		}
		sb.append(text, style);
	}

	private String rank(Card card, String direction) {
		int value = card.getRank(direction);
		return value >= 10 ? "A" : String.valueOf(value);
	}

	private String center(String text, int width) {
		int total = Math.max(0, width - text.length());
		int left = total / 2;
		return repeat(" ", left) + text + repeat(" ", total - left);
	}

	// substitui String.repeat (Java 11+)
	private String repeat(String s, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	private AttributedStyle styleOf(Player owner) {
		if (owner == null || owner.getColor() == null) {
			return AttributedStyle.DEFAULT;
		}
		return owner.getColor().getStyle().bold();
	}
	
}
