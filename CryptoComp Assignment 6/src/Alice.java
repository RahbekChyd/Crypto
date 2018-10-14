import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Alice {
	
	private BigInteger sk;
	private int n = 10;
	private ArrayList<BigInteger> cipherTexts = new ArrayList<BigInteger>();
	private ArrayList<BigInteger> q = new ArrayList<BigInteger>();
	private ArrayList<BigInteger> r = new ArrayList<BigInteger>();
	int xBit;
	SecureRandom rand = new SecureRandom();
	
	public Alice () {
		sk = generateSk();
		generateLists();
	}
	
	public int retrieve(BigInteger c) {
		BigInteger m = ((c.mod(sk).mod(new BigInteger("2"))));
		return m.intValueExact();
	}
	
	private void generateLists() {
		for (int i = 0; i < n; i++) {
			q.add(new BigInteger(500, rand));
			r.add(new BigInteger(5, rand));
			Encryption.setPkeys(sk, q.get(i), r.get(i));
		}
	}
	
	private BigInteger generateSk() {
		BigInteger tempSk = new BigInteger(36, rand);
		while (tempSk.mod(new BigInteger("2")).compareTo(new BigInteger("1")) != 0) {
			tempSk = new BigInteger(36, rand);
		}
		return new BigInteger("8354912947");
	}
	
	public ArrayList<BigInteger> choose(int[] x) {
		for (int i = 0; i < 3; i++) {
			cipherTexts.add(Encryption.encrypt(x[i]));
		}
		return cipherTexts;
	}
}