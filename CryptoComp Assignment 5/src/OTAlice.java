import java.math.BigInteger;
import java.util.ArrayList;

public class OTAlice {
	ArrayList<Ciphertext> cipherTexts = new ArrayList<Ciphertext>();
	KeyPair eBob;
	
	public OTAlice(KeyPair eBob) {
		this.eBob = eBob;
	}
	
	public ArrayList<Ciphertext> transfer(ArrayList<PublicKey> pKeys) {
		for (int i = 0; i < pKeys.size(); i++) {
			cipherTexts.add(ElGamal.encryption(eBob.getKey(i), pKeys.get(i)));
		}
		return cipherTexts;
	}
}
