import java.math.BigInteger;
import java.util.ArrayList;

public class OT {
	Alice alice;
	Bob bob;
	BigInteger[] z;
	
	public OT(Alice alice, Bob bob) {
		this.alice = alice;
		this.bob = bob;
		
		z = new BigInteger[3];
		
		//Creating arraylist for publickeys and ciphertexts generated (m1, m2)		
		ArrayList<PublicKey> m1 = new ArrayList<PublicKey>();
		ArrayList<Ciphertext> m2 = new ArrayList<Ciphertext>();
		
		for (int i = 0; i < 3; i++) {
			OTAlice OTAlice = new OTAlice(alice.getE(i));
			OTBob OTBob = new OTBob();
			
			m1 = OTBob.choose(bob.getInput(i));
			m2 = OTAlice.transfer(m1);
			z[i] = OTBob.retrieve(m2);	
		}
	}
	
	public BigInteger[] getZ() {
		return z;
	}
}
