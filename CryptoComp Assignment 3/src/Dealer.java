import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Dealer {
	SecureRandom rand;
	private int[] x;
	private int[] y;
	
	public Dealer(int[] x, int[] y) {
		rand = new SecureRandom();
	}
	
	public int generateInt() {
		int value = rand.nextInt(2);
		return value;
	}
	
	public PackageTuple generateTriple() {
		int u = rand.nextInt(2);
		int v = rand.nextInt(2);
		int w = u * v;
		
		return new PackageTuple(u, v, w);
	}
}