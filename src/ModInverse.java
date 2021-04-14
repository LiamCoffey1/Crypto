import java.math.BigInteger;

public class ModInverse {
	
	public static int calc(int x, int mod) {
		BigInteger b = BigInteger.valueOf(x);
		BigInteger n = BigInteger.valueOf(mod);
		b = b.modInverse(n);
		return b.intValue();
	}

	public static void main(String args[]) {
		BigInteger b = new BigInteger("5");
		BigInteger n = new BigInteger("191");
		b = b.modInverse(n);
		System.out.println(b);
	}
}
