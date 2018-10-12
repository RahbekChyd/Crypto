import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Alice {
	
	private BigInteger sk;
	private int n = 128;
	private ArrayList<BigInteger> pKeys = new ArrayList<BigInteger>();
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
			q.add(new BigInteger(13^6, rand));
			r.add(new BigInteger(5, rand));
			pKeys.add(sk.multiply(q.get(i).add(r.get(i).multiply(new BigInteger("2")))));
		}
	}
	
	private BigInteger generateSk() {
		BigInteger tempSk = new BigInteger(128, rand);
		while (tempSk.mod(new BigInteger("2")) != new BigInteger("1")) {
			tempSk = new BigInteger(128, rand);
		}
		return tempSk;
	}
	
	public AliceMessage choose(int[] x) {
		for (int i = 0; i < 3; i++) {
			cipherTexts.add(encrypt(x[i]));
		}
		return new AliceMessage(pKeys, cipherTexts);
	}
	
	private BigInteger encrypt(int m) {
		BigInteger c;
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i < 80; i++) {
			int temp = rand.nextInt(128);
			if (s.contains(temp) != false) {
				s.add(temp);
			}
		}
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < s.size(); i++) {
			sum = sum.add(pKeys.get(s.get(i)));
		}
		c = sum;
		if (m == 1) c = sum.add(new BigInteger("1"));
		return c;
	}
}