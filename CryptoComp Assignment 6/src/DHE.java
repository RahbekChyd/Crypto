import java.math.BigInteger;
import java.util.ArrayList;

public class DHE {

	public static void main(String[] args) {
		String x = args[0];
		String y = args[1];
		
		int[] xBinary = new int[3];
		int[] yBinary = new int[3];

		for (int i = 0; i<3; i++) {
			if (i < 3) {
				xBinary[i] = Integer.parseInt(x.substring(i, i + 1));
				yBinary[i] = Integer.parseInt(y.substring(i, i + 1));
			}
		}
		
		//Converting the binary input to integer
		int aliceInput = Integer.parseInt(x, 2);
		int bobInput = Integer.parseInt(y, 2);
		
		//Creating arraylist for publickeys and ciphertexts generated (m1, m2)		
		AliceMessage m1;
		BigInteger m2;
		
		Alice alice = new Alice();
		Bob bob = new Bob();
		
		int z;
		ArrayList<BigInteger> overviewResult;
		
		m1 = alice.choose(xBinary);
		m2 = bob.transfer(yBinary, m1);
		z = alice.retrieve(m2);
		//overviewResult = alice.programTestRetrieve(m2);
		
		//Computing output - printing TRUE if message_i = 1 and FALSE if message_i = 2
		boolean output;

		System.out.println("Input arguments (x, y):  (" + x + ", " + y + ") \n outputs result: " + z);
		
		//Printing every ciphertext trying to be decrypted using with alice sk (which should only work for one of them)
		/*System.out.println("//////////////////////////////////////////");
		System.out.println("//////////////////////////////////////////");
		System.out.println("//////////PROGRAM TEST RESULTS////////////");
		for (int i = 0; i < overviewResult.size(); i++) {
			System.out.println(overviewResult.get(i));
		}*/

		
	}
}
