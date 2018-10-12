import java.security.SecureRandom;
import java.util.Random;

public class BeDOZa {

	public static void main(String[] args) {
		String arg1 = args[0];
		String arg2 = args[1];
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		Dealer dealer = new Dealer(x, y);
		PackageTuple[] triple = new PackageTuple[5];
		
		SecureRandom rand = new SecureRandom();
		
		for (int i = 0; i<5; i++) {
			if (i < 3) {
				x[i] = Integer.parseInt(arg1.substring(i, i + 1));
				y[i] = Integer.parseInt(arg2.substring(i, i + 1));
			}
			triple[i] = dealer.generateTriple();
		}
				
		PackageTuple[] aTripleShare = new PackageTuple[5];
		PackageTuple[] bTripleShare = new PackageTuple[5];

		//Generating shares of u,v,w - 5 times
		for (int i = 0; i<5; i++) {
			int uR = rand.nextInt(2);
			int vR = rand.nextInt(2);
			int wR = rand.nextInt(2);
			
			aTripleShare[i] = new PackageTuple(uR, vR, wR);
			bTripleShare[i] = new PackageTuple(triple[i].getU() ^ uR, triple[i].getV() ^ vR, triple[i].getW() ^ wR);
			//aTripleShare[i] = new PackageTuple(0, 0, 0);
			//bTripleShare[i] = new PackageTuple(0, 0, 0);
		}
				
		Alice alice = new Alice(x, aTripleShare);
		Bob bob = new Bob(y, bTripleShare);
		
		for (int i = 0; i<3; i++) {
			bob.receive(alice.send());
			alice.receive(bob.send());
			
			bob.computeFirstLayer(i);
			alice.computeFirstLayer(i);
			
			bob.computeSecondLayer(i);
			alice.computeSecondLayer(i);
		}
		
		bob.computeThirdLayer();
		alice.computeThirdLayer();

		bob.computeFourthLayer();
		alice.computeFourthLayer();
		
		bob.computeFithLayer();
		alice.computeFifthLayer();
		
		bob.computeSixthLayer();
		alice.computeSixthLayer();
		
		System.out.println("Input arguments (x, y):  (" + arg1 + ", " + arg2 + ") \n outputs result: " + (alice.getResult() ^ bob.getResult()));
	}
}
