import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class Alice {
	private int[] xBinary;
	private int amountOfWires = 11;
	private int k = 128;
	private KeyPair d;
	private SecureRandom rand = new SecureRandom();
	private MessageDigest digest;
	private ArrayList<KeyPair> keyPairs = new ArrayList<KeyPair>();
	private ArrayList<KeyPair> eAlice = new ArrayList<KeyPair>();
	private ArrayList<KeyPair> eBob = new ArrayList<KeyPair>();
	private ArrayList<Garbled> garbledTable = new ArrayList<Garbled>();
	
	public Alice(int [] xBinary) {
		this.xBinary = xBinary;
		try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	public KeyPair getE(int i) {
		return eAlice.get(i);
	}

	public ArrayList<Garbled> choose() {
		//Generating keyPairs for all wires
		for (int i = 0; i < amountOfWires; i++) {
			keyPairs.add(new KeyPair(new BigInteger(k, rand), new BigInteger(k, rand)));
		}
		//Generating e for each input wire
		for (int i = 0; i < 6; i++) {
			if(i < 3) eAlice.add(keyPairs.get(i));
			else eBob.add(keyPairs.get(i));
		}
		d = keyPairs.get(amountOfWires - 1);
		
		BigInteger c0, c1, c2, c3;
		for (int i = 0; i < 3; i++) {
			c0 = calcNAND(i, 0, 0, i + 6);
			c1 = calcNAND(i, 0, 1, i + 6);
			c2 = calcNAND(i, 1, 0, i + 6);
			c3 = calcNAND(i, 1, 1, i + 6);
			garbledTable.add(new Garbled(c0, c1, c2, c3));
		}
		
		c0 = calcAND(6, 7, 0, 0, 9);
		c1 = calcAND(6, 7, 0, 1, 9);
		c2 = calcAND(6, 7, 1, 0, 9);
		c3 = calcAND(6, 7, 1, 1, 9);
		garbledTable.add(new Garbled(c0, c1, c2, c3));
		
		c0 = calcAND(9, 8, 0, 0, 10);
		c1 = calcAND(9, 8, 0, 1, 10);
		c2 = calcAND(9, 8, 1, 0, 10);
		c3 = calcAND(9, 8, 1, 1, 10);
		garbledTable.add(new Garbled(c0, c1, c2, c3));

		return permute(garbledTable);
	}
	
	public BigInteger[] encode() {
		BigInteger[] X = new BigInteger[3];
		for (int i = 0; i < 3; i++) {
			X[i] = eAlice.get(i).getKey(xBinary[i]);
		}
		return X;
	}
	
	private ArrayList<Garbled> permute(ArrayList<Garbled> table) {
		ArrayList<Garbled> result = new ArrayList<Garbled>();
		for (int i = 0; i < table.size(); i++) {
			result.add(table.get(i).permute());
		}
		return result;
	}
	
	private BigInteger calcNAND(int i, int a, int b, int wire) {
		BigInteger leftXOR;
		BigInteger rightXOR;
		BigInteger hashInput;

		hashInput = new BigInteger((eAlice.get(i).getKey(a)).toString(2) + g(eBob.get(i).getKey(b)), 2);
		
		leftXOR = new BigInteger(1, digest.digest(hashInput.toByteArray()));
		rightXOR = new BigInteger(append(keyPairs.get(wire).getKey(1-((1-a)*b))), 2);
				
		return leftXOR.xor(rightXOR);		
	}
	
	private BigInteger calcAND(int l, int r, int a, int b, int wire) {
		BigInteger leftXOR;
		BigInteger rightXOR;
		BigInteger hashInput;

		hashInput = new BigInteger((keyPairs.get(l).getKey(a)).toString(2) + g(keyPairs.get(r).getKey(b)), 2);
		
		leftXOR = new BigInteger(1, digest.digest(hashInput.toByteArray()));
		rightXOR = new BigInteger(append(keyPairs.get(wire).getKey(a*b)), 2);
	
		return leftXOR.xor(rightXOR);
	}
	
	private String g (BigInteger key) {
		return (String.format("%1$" + 128 + "s", "").replace(' ', '0') + key.toString(2));
	}
	
	private String append (BigInteger key) {
		return (key.toString(2) + String.format("%1$" + 128 + "s", "").replace(' ', '0'));
	}

	public int retrieve(BigInteger m2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
