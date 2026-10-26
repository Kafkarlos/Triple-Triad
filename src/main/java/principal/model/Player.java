package principal.model;
import java.util.LinkedList;
import java.util.List;

import principal.model.enums.Color;

public class Player {
	private Color color;
	private int points;
	private LinkedList<Card> hand = new LinkedList<Card>();
	
	public Player(Color color){
		this.color = color;
		this.points = 5;
	}
	
	public void addHand(Card card) {
		hand.add(card);
	}
	
	public Card playFirstCard() {
		if(hand.isEmpty()) {
			return null;
		}
		return hand.removeFirst();
	}
	
	public int getPoints() {
		return points;
	}	
	
	public void setPoints(int point) {
		points = point;
	}
	
	public Color getColor() {
		return color;
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
}
