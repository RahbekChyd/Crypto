import java.math.BigInteger;
import java.util.ArrayList;

public class Bob {
	ArrayList<BigInteger> cipherTexts = new ArrayList<BigInteger>();
	int[] y;
	
	public Bob() {
	}
	
	public BigInteger transfer(int[] y, ArrayList<BigInteger> aliceCipherTexts) {
		for (int i = 0; i < 3; i++) {
			cipherTexts.add(Encryption.encrypt(y[i]));
		}	
		return bloodFunction(aliceCipherTexts, cipherTexts);
	}
	
	public BigInteger bloodFunction(ArrayList<BigInteger> aliceCipherTexts, ArrayList<BigInteger> bobCipherTexts) {
		BigInteger[] level = new BigInteger[3];
		for (int i = 0; i < 3; i++) {
			level[i] = ((aliceCipherTexts.get(i).add(new BigInteger("1"))).multiply(bobCipherTexts.get(i))).add(new BigInteger("1"));
	
			System.out.println("LEVEL: " + i + "  :" + ((level[i].mod(new BigInteger("8354912947")).mod(new BigInteger("2")))));
		}
		BigInteger tempresult = (level[0].multiply(level[1]));
		BigInteger result = tempresult.multiply(level[2]);
		System.out.println("DONE :" + ((result).mod(new BigInteger("8354912947"))).mod(new BigInteger("2")));
		return (level[0].multiply(level[1])).multiply(level[2]);
	}
}
