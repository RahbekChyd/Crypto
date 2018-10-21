import java.math.BigInteger;

public class PublicKey {
	BigInteger p, g, h;
	
	public PublicKey(BigInteger p, BigInteger g, BigInteger h) {
		this.g = g;
		this.h = h;
		this.p = p;
	}
}


