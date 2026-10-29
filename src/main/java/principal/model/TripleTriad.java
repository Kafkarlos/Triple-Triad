package principal.model;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import principal.model.enums.Color;


public class TripleTriad {
	private Player player1;
	private Player player2;
	private Board board;
	private static final int QTDCARDS = 10;
	private static Card[] cards = new Card[QTDCARDS];
	private static final int ROWBOARD = 3;
	private static final int COLBOARD = 3;
	
	public TripleTriad(Player player1,Player player2) {
		this.player1  = player1;
		this.player2 = player2;
		board = new Board(ROWBOARD,COLBOARD);
		}
	
	public void createRandomCards() {
		Set<Integer> numsRand = generateRandomNumbers(QTDCARDS,100);
		
		Map<Integer, LinkedList<String>> cardsData = ReadCardsData(numsRand);
				
		int cardsCreate = 0;
		
		for(Map.Entry<Integer, LinkedList<String>> entry : cardsData.entrySet()) {
			int id = entry.getKey();
			
			LinkedList<String> values = entry.getValue();
			values.removeFirst();
			String name = values.removeFirst();
			String ranks = values.removeFirst();
			
			HashMap<String,Integer> ranksCard = new HashMap<>();
			List<String> cardDirection = new ArrayList<>();
			List<Integer> cardPoints = new ArrayList<>();
			
			Pattern patternPoints = Pattern.compile("\\d+");
			
			Pattern patternDirection = Pattern.compile("[a-zA-Z]+");
			
			Matcher matcherPoints = patternPoints.matcher(ranks);
			
			Matcher matcherDirection = patternDirection.matcher(ranks);
			
			while (matcherPoints.find()) {
			    cardPoints.add(Integer.parseInt(matcherPoints.group()));
			}
			
			while (matcherDirection.find()) {
			    cardDirection.add(matcherDirection.group());
			}
			
			for (int i = 0; i < cardDirection.size(); i++) {
			    ranksCard.put(cardDirection.get(i), cardPoints.get(i));
			}
			
			String element = values.removeFirst();
			
			Card cardNew = new Card(id,name,element,ranksCard);
			
			cards[cardsCreate++] = cardNew;
		}
	}
	
	public Set<Integer> generateRandomNumbers(int qtd, int bound) {
		Set<Integer> nums = new HashSet<>(); 
		Random rand = new Random();
		
		while(nums.size() < qtd) {
			int numRand = rand.nextInt(bound) + 1;
			nums.add(numRand);
		}	
		return nums;
	}
	
	public HashMap<Integer,LinkedList<String>> ReadCardsData(Set<Integer> indexes){
		HashMap<Integer,LinkedList<String>> cardsData =  new HashMap<Integer, LinkedList<String>>();
				
		InputStream input = TripleTriad.class.getResourceAsStream("/data/cards.csv");
		
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(input,StandardCharsets.UTF_16))){
				String line;
				
				 reader.readLine();

			        while ((line = reader.readLine()) != null) {

			            String[] values = line.split(";");

			            int id = Integer.parseInt(values[0].trim());

			            if (indexes.contains(id)) {
			            	
			                cardsData.put(id, new LinkedList<>(Arrays.asList(values)));
			            }
			        }
				 
			    } catch(IOException e) {
			    	e.printStackTrace();
			    }

			    return cardsData;
	}
	
	public void dealTheCards() {
		for(int i = 0;i < QTDCARDS;i++) {
			Card cardCurrent = cards[i];
			if(i < QTDCARDS / 2) {
				cardCurrent.setOwner(player1);
				player1.addHand(cards[i]);
			} else {
				cardCurrent.setOwner(player2);
				player2.addHand(cards[i]);;
			}
		}
	}
	
	public void suddenDeath() {
		board = new Board(3,3);
		for(Card card: cards) {
			card.getOwner().addHand(card);
		}
	}
	
	public boolean playCard(int row, int col, int turn) {
		if(board.checkPositionOccupied(row, col)) {
			return false;
		}
		Card cardAdded;
		if(turn % 2 == 0) {
			cardAdded = player1.playFirstCard();
		} else {
			cardAdded = player2.playFirstCard();
		}
		board.addCard(cardAdded, row, col);
		board.compare(row, col);
		return true;
	}
	
	public boolean checkPosition(int row,int col) {
		return row < ROWBOARD && row >= 0 && col >= 0 && col < COLBOARD;
	}
	
	public Color verifyMatchResult() {
		Color colorOwner;
		if(player1.getPoints() > player2.getPoints()) {
			colorOwner = player1.getColor();
		} else if(player1.getPoints() < player2.getPoints()) {
			colorOwner = player2.getColor();
		} else {
			colorOwner = Color.GRAY;
		}
		return colorOwner;
	}
	
	public String gameScenario(int turn) {
		Player current  = (turn % 2 == 0) ? player1 : player2;
		Player opponent = (turn % 2 == 0) ? player2 : player1;

		final int boardWidth = 3 * 13 + 2;      // 3 cartas de 13 colunas + 2 espaços
		final String gap = "   ";

		// tabuleiro (usa Board.toString)
		String[] boardLines = board.toString().split("\n");

		// cartas visíveis do oponente, cada uma acompanhada do seu índice na mão
		List<Card> opponentHand = opponent.getHand();
		List<String> sideLines = new ArrayList<String>();
		for (Card c : opponent.getVisibleCards()) {
			String[] lines = c.toString().split("\n");
			int index = opponentHand.indexOf(c);
			for (int i = 0; i < lines.length; i++) {
				if (i == lines.length / 2) {
					sideLines.add(lines[i] + " [" + index + "]");
				} else {
					sideLines.add(lines[i]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		// cabeçalho da coluna do oponente
		sb.append(String.format("%" + (boardWidth + gap.length()) + "s", ""));
		sb.append("Mão do oponente\n");

		for (int i = 0; i < boardLines.length; i++) {
			sb.append(boardLines[i]);
			if (i < sideLines.size()) {
				sb.append(gap).append(sideLines.get(i));
			}
			sb.append("\n");
		}

		// mão do jogador da vez, na horizontal (usa Card.toString)
		sb.append("\nSua mão\n");
		List<Card> hand = current.getHand();
		if (!hand.isEmpty()) {
			String[][] handLines = new String[hand.size()][];
			for (int i = 0; i < hand.size(); i++) {
				handLines[i] = hand.get(i).toString().split("\n");
			}
			for (int line = 0; line < handLines[0].length; line++) {
				for (int i = 0; i < handLines.length; i++) {
					sb.append(handLines[i][line]).append(" ");
				}
				sb.append("\n");
			}
		}

		return sb.toString();
	}
}
