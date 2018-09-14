import java.util.List;
import java.util.Random;

public class Dealer {
	
	public Dealer() {
	}
	
	public int generateInt() {
		Random rand = new Random();
		int value = rand.nextInt(2);
		return value;
	}
}
