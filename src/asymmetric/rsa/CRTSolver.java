package asymmetric.rsa;

import java.math.BigInteger;

class Cypher {
	public BigInteger pubMod, crypt;

	public Cypher(BigInteger pubMod, BigInteger crypt) {
		this.pubMod = pubMod;
		this.crypt = crypt;
	}
}

public class CRTSolver {
	int E = 5;
	BigInteger root(int n, BigInteger x) {
		BigInteger y = BigInteger.ZERO;
		for (int m = (x.bitLength() - 1) / n; m >= 0; --m) {
			BigInteger z = y.setBit(m);
			int cmp = z.pow(n).compareTo(x);
			if (cmp == 0)
				return z; // found exact root
			if (cmp < 0)
				y = z; // keep bit set
		}
		return y; // return floor of exact root
	};
	
	Cypher[] pairs = new Cypher[] {
			new Cypher(new BigInteger("4459740564724538700519142326997"),
					new BigInteger("191699250018696932235548276819")),
			new Cypher(new BigInteger("2281806784635143785292256501293"),
					new BigInteger("2262766903820045933008151262425")),
			new Cypher(new BigInteger("2467881921864340392351277277159"),
					new BigInteger("1643829619688527494120778009131")) };

	public CRTSolver() {
		BigInteger M = new BigInteger("1");
		BigInteger sum = new BigInteger("0");
		for (Cypher c : pairs) {
			M = M.multiply(c.pubMod);
		}
		for (Cypher c : pairs) {
			BigInteger Mi = M.divide(c.pubMod);
			BigInteger Ni = Mi.modInverse(c.pubMod);
			sum = sum.add(Mi.multiply(Ni).multiply(c.crypt));
		}
		BigInteger P = sum.mod(M);
		System.out.println("P:");
		System.out.println(root(3, P).toString());

	}

	public static void main(String args[]) {
		new CRTSolver();
	}
}
