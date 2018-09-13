import java.util.List;
import java.util.Random;

public class Dealer {
	private int[][] aliceMatrix;
	private int[][] truthTable;
	
	public Dealer() {
		truthTable = new int[8][8];
		generateTruthValues();
	}
	
	public String generateString() {
		Random rand = new Random();
		int value = rand.nextInt(8);
		return Utility.padding(Integer.toBinaryString(value));
	}
	
	public int[][] generateMatrix() {
		int[][] bobMatrix = new int[8][8];
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				Random r = new Random();
				bobMatrix[i][j] = r.nextInt(2);
			}
		}
		return bobMatrix;
	}
	
	public int[][] computeAliceMatrix(String r, String s, int[][] bobMatrix) {
		aliceMatrix = new int[8][8];
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				aliceMatrix[i][j] = bobMatrix[i][j] ^ truthTable[Math.floorMod(i - Integer.parseInt(r, 2), 8)][Math.floorMod(j - Integer.parseInt(s, 2), 8)];
			}
		}
		return aliceMatrix;
	}
	
	private void generateTruthValues() {
		truthTable = new int[][]{
            { 1, 0, 0, 0, 0, 0, 0, 0},
            { 1, 1, 0, 0, 0, 0, 0, 0},
            { 1, 0, 1, 0, 0, 0, 0, 0},
            { 1, 1, 1, 1, 0, 0, 0, 0},
            { 1, 0, 0, 0, 1, 0, 0, 0},
            { 1, 1, 0, 0, 1, 1, 0, 0},
            { 1, 0, 1, 0, 1, 0, 1, 0},
            { 1, 1, 1, 1, 1, 1, 1, 1}};
	}
}
