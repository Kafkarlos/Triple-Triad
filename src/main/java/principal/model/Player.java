package principal.model;
import java.util.ArrayList;
import java.util.Collections;
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
		if(!checkCardInHand(card)) {
			hand.add(card);
		}
	}
	
	public boolean checkCardInHand(Card newCard) {
		for(Card card: hand) {
			if(card == newCard) {
				return true;
			}
		}
		return false;
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
	
	
	public void initVisibleCards() {
		List<Card> tempList = new ArrayList<>(hand);
		Collections.shuffle(tempList);
		int count = Math.min(3, tempList.size());
		for (int i = 0; i < count; i++) {
			tempList.get(i).setVisible(true);
		}
	}

	public List<Card> getVisibleCards() {
		List<Card> visible = new ArrayList<Card>();
		for (Card c : hand) {
			if (c.isVisible()) {
				visible.add(c);
			}
		}
		return visible;
	}

	public void hideCards() {
		for (Card c : hand) {
			c.setVisible(false);
		}
	}
}
