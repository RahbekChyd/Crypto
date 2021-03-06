import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DHE {

	public static void main(String[] args) {
		//Worst case with 'r = 2 bits' is (2^2*n) where n is 10 (public key size) which is 40. This can be expressed with 6 bits 2^6=64.
		//In this curcuit we do 5 multiplications which means that sk should be at least 30 bits. I set mine to 36 because every homomorphic operations adds noise and we do addition as well.
	
		String x = args[0];
		String y = args[1];
		
		int[] xBinary = new int[3];
		int[] yBinary = new int[3];

		for (int i = 0; i<3; i++) {
			if (i < 3) {
				xBinary[i] = Integer.parseInt(x.substring(i, i + 1));
				yBinary[i] = Integer.parseInt(y.substring(i, i + 1));
			}
		}
		
		//Creating arraylist for alices ciphertexts and result of bob evaluating function (m1, m2)		
		ArrayList<BigInteger> m1 = new ArrayList<BigInteger>();
		BigInteger m2;
		
		Alice alice = new Alice();
		Bob bob = new Bob();
		
		int z;
				
		m1 = alice.choose(xBinary);
		m2 = bob.transfer(yBinary, m1);
		z = alice.retrieve(m2);
		
		System.out.println("Input arguments (x, y):  (" + x + ", " + y + ") \n outputs result: " + z);		
	}
}
