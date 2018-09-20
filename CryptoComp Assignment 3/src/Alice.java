import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Alice {
	private static int d;
	private static int e;
	private ArrayList<Integer> aliceShares;
	private ArrayList<Integer> sharesFromBob;
	private int[] z;
	private int[] x;
	private int zResult;
	private int output;
	private PackageTuple[] triple;
	private SecureRandom rand;
	private int andCount = 0;
	
	public Alice (int[] x, PackageTuple[] triple) {
		this.x = x;
		this.triple = triple;
		z = new int[3];
		aliceShares = new ArrayList<Integer>();
		sharesFromBob = new ArrayList<Integer>();
		rand = new SecureRandom();
		System.out.println(triple[0].getU() + "  " + triple[0].getV() + "   " + triple[0].getW());
	}
	
	public static int getD() {
		return d;
	}
	
	public static int getE() {
		return e;
	}
	
	//Computing (1 XOR x) and setting d and e shares for second layer
	public void computeFirstLayer(int i) {
		z[i] = Curcuit.not(aliceShares.get(i));
		d = Curcuit.xorTwo(z[i], triple[andCount].getU());
		e = Curcuit.xorTwo(sharesFromBob.get(i), triple[andCount].getV());
	}
	
	//Computing (1 XOR (Result from first layer AND share from Bob)). Here the first AND is computed and therefore 
	//done with current triple.
	public void computeSecondLayer(int i) {
		int openD = d ^ Bob.getD();
		int openE = e ^ Bob.getE();
		System.out.println("AND count:  " + andCount);
		z[i] = Curcuit.not(Curcuit.andTwo("alice", triple[andCount].getW(), openD, openE, z[i], sharesFromBob.get(i)));
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
		int openD = d ^ Bob.getD();
		int openE = e ^ Bob.getE();
		System.out.println("AND count:  " + andCount);
		zResult = Curcuit.andTwo("alice", triple[andCount].getW(), openD, openE, z[0], z[1]);
		andCount++;
	}
	
	//Computing new shares d and e for the result of previous layer
	public void computeFifthLayer() {
		d = Curcuit.xorTwo(zResult, triple[andCount].getU());
		e = Curcuit.xorTwo(z[2], triple[andCount].getV());
	}
	
	//Computing AND of result from previous layer with last tuple
	public void computeSixthLayer() {
		int openD = d ^ Bob.getD();
		int openE = e ^ Bob.getE();
		System.out.println("AND count:  " + andCount);
		zResult = Curcuit.andTwo("alice", triple[andCount].getW(), openD, openE, zResult, z[2]);
		setResult(zResult);
	}
	
	public void setResult(int x) {
		output = x;
	}
	
	public int getResult() {
		return output;
	}

	public void receive(int received) {
		sharesFromBob.add(received);
	}
	
	public int send() {
		int bobShare = rand.nextInt(2);
		int tempShare = x[aliceShares.size()] ^ bobShare;
		aliceShares.add(tempShare);
		return bobShare;
	}
}
