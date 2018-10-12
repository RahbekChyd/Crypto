import java.math.BigInteger;
import java.util.ArrayList;

public class Bob {
	ArrayList<Ciphertext> cipherTexts = new ArrayList<Ciphertext>();
	int[] y;
	AliceMessage am;
	
	public Bob() {
	}
	
	public BigInteger transfer(int[] y, AliceMessage am) {
		return new BigInteger("2");
	}
	
	public int bloodFunction(int[] x, int i) {
		int[] y = new int[3];
		y = Utility.binaryInt(i);
		boolean result = (((x[0] <= y[0]) && (x[1] <= y[1])) && (x[2] <= y[2]));
		return (result) ? 1 : 2;
	}
}
