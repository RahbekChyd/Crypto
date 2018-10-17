import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Alice {
	
	private BigInteger sk;
	private int n = 10; //size of public key (number of y_i)
	private ArrayList<BigInteger> cipherTexts = new ArrayList<BigInteger>();
	private ArrayList<BigInteger> q = new ArrayList<BigInteger>();
	private ArrayList<BigInteger> r = new ArrayList<BigInteger>();
	SecureRandom rand = new SecureRandom();
	
	public Alice () {
		sk = generateSk();
		generateLists();
	}
	
	public int retrieve(BigInteger c) {
		BigInteger m = ((c.mod(sk)).mod(new BigInteger("2")));
		return m.intValueExact();
	}
	
	private void generateLists() {
		//Generating list q and r, and making public keys with those and the secret key
		for (int i = 0; i < n; i++) {
			//Size of 'q' is 500 and size of 'r' is 2
			q.add(new BigInteger(500, rand));
			r.add(new BigInteger(2, rand));
			Encryption.setPkeys(sk, q.get(i), r.get(i));
		}
	}
	
	private BigInteger generateSk() {
		//Generating secret key - with a size of 36
		BigInteger tempSk = BigInteger.probablePrime(36, rand);
		while (tempSk.mod(new BigInteger("2")).compareTo(new BigInteger("1")) != 0) {
			tempSk = BigInteger.probablePrime(36, rand);
		}
		return tempSk;
	}
	
	public ArrayList<BigInteger> choose(int[] x) {
		for (int i = 0; i < 3; i++) {
			cipherTexts.add(Encryption.encrypt(x[i]));
		}
		return cipherTexts;
	}
}