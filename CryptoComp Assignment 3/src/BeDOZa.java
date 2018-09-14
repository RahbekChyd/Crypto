import java.util.Random;

public class BeDOZa {

	public static void main(String[] args) {
		String x = args[0];
		String y = args[1];
			
		Dealer dealer = new Dealer();
		int r = dealer.generateInt();
		int s = dealer.generateInt();
		
		Alice alice = new Alice(x, r);
		Bob bob = new Bob(y, s);
		
		for (int i = 1; i<4; i++) {
			bob.recieve(alice.send(i));
			alice.receive(bob.send(i));
		}
		
		
		System.out.println("Input arguments (x, y):  (" + x + ", " + y + ") \n outputs result: " + alice.result());
	}
}
