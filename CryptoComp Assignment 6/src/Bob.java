import java.math.BigInteger;
import java.util.ArrayList;

public class Bob {
	ArrayList<BigInteger> cipherTexts = new ArrayList<BigInteger>();
	int[] y;
	
	public Bob() {
	}
	
	public BigInteger transfer(int[] y, ArrayList<BigInteger> aliceCipherTexts) {
		//Generating bob's ciphertexts from his input and then return the result of evaluation
		for (int i = 0; i < 3; i++) {
			cipherTexts.add(Encryption.encrypt(y[i]));
		}	
		return bloodFunction(aliceCipherTexts, cipherTexts);
	}
	
	public BigInteger bloodFunction(ArrayList<BigInteger> aliceCipherTexts, ArrayList<BigInteger> bobCipherTexts) {
		BigInteger[] cipher = new BigInteger[3];
		//Calculating not(and(not(aliceCipherTexts(i)), bobCipherTexts(i)))
		for (int i = 0; i < 3; i++) {
			cipher[i] = ((aliceCipherTexts.get(i).add(new BigInteger("1"))).multiply(bobCipherTexts.get(i))).add(new BigInteger("1"));
			}

		//Calculating and(and(cipher(0), cipher(1)), cipher(2))
		return (cipher[0].multiply(cipher[1])).multiply(cipher[2]);
	}
}