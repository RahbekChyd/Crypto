
public class Curcuit {	
	public static int xorConst(int x, int c) {
		return x ^ c;
	}
	
	public static int andConst(int x, int c) {
		return c * x;
	}
	
	public static int xorTwo(int x, int y) {
		return x ^ y;
	}
	
	public static int not(int x) {
		return xorConst(x, 1);
	}
	
	
	//NOTE: I skip the edit for xorConst bob by only considering first andConst(d, y) in the AND???
	public static int andTwo(String id, int w, int d, int e, int x, int y) {
		if (id.equals("bob")) {
			return xorTwo(w, (xorTwo(andConst(e, x), andConst(d, y))));
		}
		else {
			return xorTwo(w, (xorTwo(andConst(e, x), xorConst(andConst(d, y), andConst(e, d)))));
		}
	}
}
