package asymmetric.rsa;

import java.math.BigInteger;

public class PrivateKey {
	public static void main(String args[]) {
		BigInteger e = BigInteger.valueOf(10007);
		BigInteger p = BigInteger.valueOf(2011);
		BigInteger q = BigInteger.valueOf(10007);
		
		BigInteger d = e.modInverse(p.multiply(q));
		System.out.println(d.intValue());
	}
}
