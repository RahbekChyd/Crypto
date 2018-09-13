import java.util.Random;

public class OTTT {

	public static void main(String[] args) {
		String x = args[0];
		String y = args[1];
			
		Dealer dealer = new Dealer();
		String r = dealer.generateString();
		String s = dealer.generateString();
								
		int[][] bobMatrix = dealer.generateMatrix();
		int[][] aliceMatrix = dealer.computeAliceMatrix(r, s, bobMatrix);
		
		Alice alice = new Alice(x, r, aliceMatrix);
		Bob bob = new Bob(y, s, bobMatrix);
		
		bob.recieve(alice.send());
				
		alice.receive(bob.send());
		
		System.out.println("Input arguments (x, y):  (" + x + ", " + y + ") \n outputs result: " + alice.result());
	}
}
