import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Garbled {
	BigInteger c0, c1, c2, c3;
	ArrayList<BigInteger> accessC = new ArrayList<BigInteger>();
	public Garbled(BigInteger c0, BigInteger c1, BigInteger c2, BigInteger c3) {
		this.c0 = c0;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		accessC.add(c0);
		accessC.add(c1);
		accessC.add(c2);
		accessC.add(c3);
	}
	
	public Garbled permute() {
		ArrayList<BigInteger> prePermuteArray = new ArrayList<BigInteger>();
		ArrayList<BigInteger> postPermuteArray = new ArrayList<BigInteger>();
		prePermuteArray.add(c0);
		prePermuteArray.add(c1);
		prePermuteArray.add(c2);
		prePermuteArray.add(c3);
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			int pick = rand.nextInt(prePermuteArray.size());
			postPermuteArray.add(prePermuteArray.get(pick));
			prePermuteArray.remove(pick);
		}	
		return new Garbled(postPermuteArray.get(0), postPermuteArray.get(1), postPermuteArray.get(2), postPermuteArray.get(3));
	}
	
	public BigInteger getC(int i) {			
		return accessC.get(i);
	}
}
