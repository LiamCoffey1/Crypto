package asymmetric;

import java.math.BigInteger;
import java.security.SecureRandom;

class Handler {

	int MAX_KEY_RANGE = 1000000;
	int MINUMUM_PRIME = 3;


	public Handler() {
		generateKeys();
	}

	boolean isPrime(int x) {
		for (int i = 2; i < x; i++)
			if (x % i == 0)
				return false;
		return true;
	}

	int randomRange(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	int x;
	BigInteger a, b, y, P, G;

	void generateKeys() {
		P = BigInteger.probablePrime(64, new SecureRandom()); // 64 bit random prime
		G = new BigInteger("" + randomRange(1, P.intValue() - 1)); // Generator G < P
		x = randomRange(MINUMUM_PRIME, MAX_KEY_RANGE); // x random number
		y = G.pow(x).mod(P); // y = G^x % P
	}

	BigInteger[] encrypt(BigInteger msg) {
		BigInteger k = new BigInteger("" + randomRange(MINUMUM_PRIME, MAX_KEY_RANGE-1)); // k in range of X and P
		BigInteger a = G.modPow(k, P);
		BigInteger b = msg.multiply(y.modPow(k, P)); // (G^k % P) * (y^k % P)
		return new BigInteger[] { a, b };
	}

	/*
	 * Pk1, first part of public key
	 * Pk2, second part of public key
	 */
	String decrypt(BigInteger pk1, BigInteger pk2) {
		BigInteger bigX = new BigInteger("" + x);
		BigInteger crmodp = pk1.modPow(bigX, P);
		BigInteger d = crmodp.modInverse(P);
		BigInteger ad = d.multiply(pk2).mod(P); // (((key1 ^ x) % P)^-1 % P) * (key2 % P)
		return ad.toString();
	}
}

public class ElGamal {

	public static void main(String args[]) {
		Handler h = new Handler(); // Generate Keys
		int msg = 1500000000;
		BigInteger[] encrypted = h.encrypt(BigInteger.valueOf(msg)); // Encrypt M with gen keys
		System.out.println("Plaintext:" + msg);
		System.out.println("CypherText: (" + encrypted[0] + "," + encrypted[1] + ")");
		System.out.println("Decrypted:" + h.decrypt(encrypted[0], encrypted[1])); // Decrypt C with gen keys

	}

}
