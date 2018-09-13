import java.util.List;

public class Alice {
	private String random;
	private int[][] matrix;
	private int u;
	private String x;
	private PackageTuple rPackage;
	
	public Alice (String x, String random, int[][] matrix) {
		this.random = random;
		this.matrix = matrix;
		this.x = x;
		computeU();
	}
	
	public void computeU() {
		int tempU = (Integer.parseInt(x, 2) + Integer.parseInt(random, 2)) % 8;
		if (tempU < 0) {
			System.out.println(tempU);
			tempU = tempU + 8;
		}
		u = tempU;
	}
	
	public int result() {
		int z = matrix[u][rPackage.getV()] ^ rPackage.getzB();
		return z;
	}

	public void receive(PackageTuple received) {
		rPackage = received;
	}
	
	public int send() {
		return u;
	}
}
