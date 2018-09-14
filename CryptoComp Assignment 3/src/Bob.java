import java.util.List;

public class Bob {
	private int bobRandom;
	private int aliceRandom;
	private int[][] matrix;
	private int u;
	private int v;
	private int x_b;
	private int x_r;
	private String y;
	
	public Bob (String y, int bobRandom) {
		this.bobRandom = bobRandom;
		this.matrix = matrix;
		this.y = y;
		computeX_B(1);
	}
	
	private void computeX_B(int wireN) {
		x_b = Integer.parseInt(y.substring(wireN-1,wireN)) ^ bobRandom;
	}
	
	private int zB() {
		return matrix[u][v];
	}
	
	public void recieve(int received) {
		aliceRandom = received;
	}
	
	public int send(int wire) {
		computeX_B(wire);
		return bobRandom;
	}
}
