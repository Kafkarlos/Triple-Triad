package principal.model;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TripleTriad {
	private Player player1;
	private Player player2;
	private Board board;
	Card[] Card = new Card[10];
	
	public TripleTriad(Player player1,Player player2) {
		this.player1  = player1;
		this.player2 = player2;
		//board = new Board(3,3);
		}
	
	public void createRandomCards() {
		Set<Integer> numsRand = generateRandomNumbers(10,100);
		
		Map<Integer,List<String>> cardsData = ReadCardsData(numsRand);
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
	
	public Map<Integer, List<String>> ReadCardsData(Set<Integer> indexes){
		Map<Integer,List<String>> cardData =  new HashMap<Integer, List<String>>();
				
		InputStream input = TripleTriad.class.getResourceAsStream("/data/cards.csv");
		
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(input,StandardCharsets.UTF_16))){
				String line;
				
				 reader.readLine();

			        while ((line = reader.readLine()) != null) {

			            String[] values = line.split(";");

			            int id = Integer.parseInt(values[0].trim());

			            if (indexes.contains(id)) {
			                cardData.put(id, List.of(values));
			            }
			        }
				 
			    } catch(IOException e) {
			    	e.printStackTrace();
			    }

			    return cardData;
	}
	
}
