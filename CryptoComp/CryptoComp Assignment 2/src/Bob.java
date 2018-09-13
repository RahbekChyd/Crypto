import java.util.List;

public class Bob {
	private String random;
	private int[][] matrix;
	private int u;
	private int v;
	private String y;
	
	public Bob (String y, String random, int[][] matrix) {
		this.random = random;
		this.matrix = matrix;
		this.y = y;
		computeV();
	}
	
	private void computeV() {
		int tempV = (Integer.parseInt(y, 2) + Integer.parseInt(random, 2)) % 8;
		if (tempV < 0) {
			System.out.println(tempV);
			tempV = tempV + 8;
		}
		v = tempV;
	}
	
	private int zB() {
		return matrix[u][v];
	}
	
	public void recieve(int input) {
		this.u = input;
	}
	
	public PackageTuple send() {
		return new PackageTuple(v, zB());
	}
}
