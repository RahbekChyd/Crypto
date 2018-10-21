import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class OTBob {
	private BigInteger sk = new BigInteger("24564911555850674068891473901416916365986486704035329587167860279749751950600");
	private ArrayList<PublicKey> pKeys = new ArrayList<PublicKey>();
	int binaryInput;
	int xBit;
	SecureRandom rand = new SecureRandom();
	ElGamal elGamal = new ElGamal();
	
	public OTBob () {	}
	
	//Generating pkeys with the i'th key using GEN() and rest OGen()
	public ArrayList<PublicKey> choose(int x) {
		xBit = x;
		for (int i = 0; i<2; i++) {
			if (i == x) {
				pKeys.add(elGamal.generatePk(sk));
			}
			else {
				pKeys.add(elGamal.oGeneratePk(generateRandomBigInt()));
			}
		}
		return pKeys;
	}
	
	public BigInteger retrieve(ArrayList<Ciphertext> cipherTexts) {
		return elGamal.decryption(cipherTexts.get(xBit), sk);
	}
	
	//Generating random biginteger for OGen()
	private BigInteger generateRandomBigInt() {
		BigInteger result = new BigInteger(255, rand);
		return result;
	}
}
