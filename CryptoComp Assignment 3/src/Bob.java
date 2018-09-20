import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Bob {
	private ArrayList<Integer> bobShares;
	private ArrayList<Integer> sharesFromAlice;
	private int[] y;
	private SecureRandom rand;
	private int output;
	private int zResult;
	private int andCount = 0;
	private PackageTuple[] triple;
	private int[] z;
	private static int d;
	private static int e;
	
	public Bob (int[] y, PackageTuple[] triple) {
		this.triple = triple;
		this.y = y;
		z = new int[3];
		bobShares = new ArrayList<Integer>();
		sharesFromAlice = new ArrayList<Integer>();
		rand = new SecureRandom();
		System.out.println(triple[0].getU() + "  " + triple[0].getV() + "   " + triple[0].getW());
	}
	
	//NOTE: Should it be z[i] = Curcuit.not(sharesFromAlice.get(i)); ????
	//Computing (1 XOR share from alice) and setting d and e shares for second layer
	public void computeFirstLayer(int i) {
		z[i] = sharesFromAlice.get(i);
		d = Curcuit.xorTwo(z[i], triple[andCount].getU());
		e = Curcuit.xorTwo(bobShares.get(i), triple[andCount].getV());
	}
	
	//NOTE: Should I add z[i] = Curcuit.not(z[i]); ????
	//Computing (1 XOR (Result from first layer AND bobShare)). Here the first AND is computed and therefore 
	//done with current triple.
	public void computeSecondLayer(int i) {
		int openD = d ^ Alice.getD();
		int openE = e ^ Alice.getE();
		z[i] = Curcuit.andTwo("bob", triple[andCount].getW(), openD, openE, z[i], bobShares.get(i));
		andCount++;	
	}
	
	//Now all 3 iterations of first and second layer is done and on to ANDing the results
	//Here we compute new d and e shares for 2nd AND
	public void computeThirdLayer() {
		d = Curcuit.xorTwo(z[0], triple[andCount].getU());
		e = Curcuit.xorTwo(z[1], triple[andCount].getV());
	}
	
	//Computing 2nd AND
	public void computeFourthLayer() {
		int openD = d ^ Alice.getD();
		int openE = e ^ Alice.getE();
		zResult = Curcuit.andTwo("bob", triple[andCount].getW(), openD, openE, z[0], z[1]);
		andCount++;
	}
	
	//Computing new shares d and e for the result of previous layer
	public void computeFithLayer() {
		d = Curcuit.xorTwo(zResult, triple[andCount].getU());
		e = Curcuit.xorTwo(z[2], triple[andCount].getV());
	}
	
	//Computing AND of result from previous layer with last tuple
	public void computeSixthLayer() {
		int openD = d ^ Alice.getD();
		int openE = e ^ Alice.getE();
		zResult = Curcuit.andTwo("bob", triple[andCount].getW(), openD, openE, zResult, z[2]);
		setResult(zResult);
	}

	public void setResult(int x) {
		output = x;
	}
	
	public int getResult() {
		return output;
	}
	
	public static int getD() {
		return d;
	}
	
	public static int getE() {
		return e;
	}
	
	public void receive(int received) {
		sharesFromAlice.add(received);
	}
	
	public int send() {
		int aliceShare = rand.nextInt(2);
		int tempShare = y[bobShares.size()] ^ aliceShare;
		bobShares.add(tempShare);
		return aliceShare;
	}
}
