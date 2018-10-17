import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Encryption {
	private static ArrayList<BigInteger> pKeys = new ArrayList<BigInteger>();
	private static SecureRandom rand = new SecureRandom();
	
	public static BigInteger encrypt(int m) {
		//Creating the subset S with |S| = 8. Even though I argued our worst case of 2(sum(r_i)) with n = 10, this is a bit lower (8), but is still secure since it adds randomness.
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			int temp = rand.nextInt(10);
			if (s.contains(temp) == false) {
				s.add(temp);
			}
		}
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < s.size(); i++) {
			sum = sum.add(Encryption.getPkeys().get(s.get(i)));
		}
	
		return sum.add(BigInteger.valueOf(m));
	}
	
	public static ArrayList<BigInteger> getPkeys() {
		return pKeys;
	}

	public static void setPkeys(BigInteger sk, BigInteger q, BigInteger r) {
		pKeys.add((sk.multiply(q)).add(r.multiply(new BigInteger("2"))));
	}
}
