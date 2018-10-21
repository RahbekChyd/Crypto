import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ElGamal {
	BigInteger h, g, p, q;
	private static SecureRandom rand = new SecureRandom();
	private static MessageDigest digest;
	
	public ElGamal() {
		p = generateP();
		q = generateQ();
		h = new BigInteger(255, rand);
		g = generateG();
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	private BigInteger generateG() {
		BigInteger tempG = h.modPow((p.subtract(BigInteger.ONE).divide(q)), p);
		boolean checkOrder = tempG.modPow(q, p).intValue() == 1;
		while(!checkOrder) {
			h = new BigInteger(255, rand);
			tempG = h.modPow((p.subtract(BigInteger.ONE).divide(q)), p);
			checkOrder = tempG.modPow(q, p).intValue() == 1;
		}
		return tempG;
	}
	
	private BigInteger generateP() {
		BigInteger tempP = BigInteger.probablePrime(256, rand);
		//Finding safe prime with certainty 10 which is around 99.9%
		boolean check = tempP.subtract(BigInteger.ONE).divide(BigInteger.TWO).isProbablePrime(10);
		while(!check) {
			tempP = BigInteger.probablePrime(256, rand);
			check = tempP.subtract(BigInteger.ONE).divide(BigInteger.TWO).isProbablePrime(10);
		}
		return tempP;
	}
	
	private BigInteger generateQ() {
		return p.subtract(BigInteger.ONE).divide(BigInteger.TWO);
	}
	
	public PublicKey generatePk(BigInteger sk) {
		return new PublicKey(p, g, g.modPow(sk, p));
	}
	
	public PublicKey oGeneratePk(BigInteger r) {
		return new PublicKey(p, g, (r.pow(2)).mod(p));
	}
	
	public static Ciphertext encryption(BigInteger m, PublicKey pKey) {
		BigInteger r = new BigInteger(255, rand);
		BigInteger hashedValue = new BigInteger(1, digest.digest(pKey.h.modPow(r, pKey.p).toByteArray()));
		return new Ciphertext(pKey.g.modPow(r, pKey.p), m.add(hashedValue).mod(pKey.p));
	}
	
	public BigInteger decryption(Ciphertext cipherText, BigInteger sk) {
		BigInteger modularInv = cipherText.x.modPow(sk, p);
		BigInteger hashedValue = new BigInteger(1, digest.digest(modularInv.toByteArray()));
		BigInteger m = cipherText.y.subtract(hashedValue).mod(p);
		return m;
	}
	
	public BigInteger getP() {
		return p;
	}
}
