import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Bob {
	private int[] yBinary;
	private MessageDigest digest;
	private ArrayList<BigInteger> z = new ArrayList<BigInteger>();

	
	public Bob(int[] yBinary) {
		this.yBinary = yBinary;
		try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	public int getInput(int i) {
		return yBinary[i];
	}

	public BigInteger transfer(ArrayList<Garbled> garbledTable, BigInteger[] X, BigInteger[] Y) {	
		//NOT AND NOT
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				BigInteger hashInput = new BigInteger((X[i].toString(2) + g(Y[i])), 2);
				BigInteger tempCheck = (garbledTable.get(i).getC(j)).xor(new BigInteger(1, digest.digest(hashInput.toByteArray())));
				String stringTempCheck = tempCheck.toString(2);
				System.out.println(stringTempCheck);
				if (check0K(stringTempCheck.substring(stringTempCheck.length() - 128))) {
					z.add(new BigInteger(stringTempCheck.substring(0, stringTempCheck.length() - 128), 2)); 
				}
			}
		}
		
		//First AND
		for (int i = 0; i < 4; i++) {
			BigInteger hashInput = new BigInteger((z.get(0).toString(2) + g(z.get(1))), 2);
			BigInteger tempCheck = (garbledTable.get(3).getC(i)).xor(new BigInteger(1, digest.digest(hashInput.toByteArray())));
			String stringTempCheck = tempCheck.toString(2);
			if (check0K(stringTempCheck.substring(stringTempCheck.length() - 128))) {
				z.add(new BigInteger(stringTempCheck.substring(0, stringTempCheck.length() - 128), 2)); 
			}
		}
		
		//Second AND
		for (int i = 0; i < 4; i++) {
			BigInteger hashInput = new BigInteger((z.get(3).toString(2) + g(z.get(2))), 2);
			BigInteger tempCheck = (garbledTable.get(4).getC(i)).xor(new BigInteger(1, digest.digest(hashInput.toByteArray())));
			String stringTempCheck = tempCheck.toString(2);
			if (check0K(stringTempCheck.substring(stringTempCheck.length() - 128))) {
				z.add(new BigInteger(stringTempCheck.substring(0, stringTempCheck.length() - 128), 2)); 
			}
		}
		return z.get(z.size() - 1);
	}
	
	private String g (BigInteger key) {
		return (String.format("%1$" + 128 + "s", "").replace(' ', '0') + key.toString(2));
	}
	
	private boolean check0K(String x) {
		return x.equals(String.format("%1$" + 128 + "s", "").replace(' ', '0'));
	}
}
