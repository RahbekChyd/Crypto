import java.math.BigInteger;

public class KeyPair {
	BigInteger key0, key1;
	public KeyPair(BigInteger key0, BigInteger key1) {
		this.key0 = key0;
		this.key1 = key1;
	}
	
	public BigInteger getKey(int key) {
		return (key == 0) ? key0 : key1;
	}
}
