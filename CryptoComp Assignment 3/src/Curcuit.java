
public class Curcuit {
	public static int curcuitFunction(int x_a, int x_b, int x_r, int y_a, int y_b, int y_r) {
		return 0;
	}
	
	public int xorConst(String id, int x, int c) {
		if (id.equals("alice")) return x ^ c;
		else return x;
	}
	
	public int andConst(int x, int c) {
		return c * x;
	}
	
	public int xorTwo(int x, int y) {
		return x ^ y;
	}
	
	public int andTwo() {
		return 0;
	}
}
