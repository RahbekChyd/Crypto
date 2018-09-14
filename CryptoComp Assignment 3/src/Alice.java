import java.util.List;

public class Alice {
	private int aliceRandom;
	private int bobRandom;
	private int[][] matrix;
	private int x_a;
	private String x;
	private PackageTuple rPackage;
	
	public Alice (String x, int aliceRandom) {
		this.aliceRandom = aliceRandom;
		this.matrix = matrix;
		this.x = x;
		computeX_A(1);
	}
	
	public void computeX_A(int wireN) {
		x_a = Integer.parseInt(x.substring(wireN-1,wireN)) ^ aliceRandom;
	}
	
	public int result() {
		int z = matrix[x_a][rPackage.getV()] ^ rPackage.getzB();
		return z;
	}

	public void receive(int received) {
		bobRandom = received;
	}
	
	public int send(int wire) {
		computeX_A(wire);
		return aliceRandom;
	}
}
