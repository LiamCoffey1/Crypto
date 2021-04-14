package asymmetric.rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;

class Alice {
	int p = 53, q = 59, n = q * p, phi = (q - 1) * (p - 1), e = 3, // 1 < e < phi, gcd(e, phi) = 1
			d = (2 * (phi + 1)) / e;

	int[] getPublicKey() {
		return new int[] { e, n };
	}

	String decrypt(int msg, BigInteger n, BigInteger d) {
		BigInteger bigm = new BigInteger("" + msg);
		BigInteger bigmal = bigm.modPow(d, n);
		return bigmal.toString();
	}
}

class Bob {
	int encrypt(int[] key, int msg) {
		return (int) (Math.pow(msg, key[0]) % key[1]);
	}
}

public class RSA {
	
	public static KeyPair keyGen() throws Exception {
		KeyPair keypair;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("DefaultRSA_key.ser"));
			keypair = (KeyPair) in.readObject();
			in.close();
		} catch (FileNotFoundException fnfe) {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1026);
			keypair = generator.genKeyPair();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""));
			out.writeObject(keypair);
			out.close();
		}
		return keypair;
	}
	
	public static byte[] stringToBytes(String s) {
	    byte[] b2 = new BigInteger(s, 36).toByteArray();
	    return Arrays.copyOfRange(b2, 1, b2.length);
	}

	public static void main(String args[]) {
		Alice alice = new Alice();
		 byte[] decryptedBytes;

		/*Bob bob = new Bob();
		int[] publicKey = alice.getPublicKey();
		int msg = 800;
		System.out.println(msg);
		int encrypted = bob.encrypt(publicKey, msg);
		System.out.println("Encrypted: " + encrypted);*/
		String cypher = "MW9+FZTFllohILd0mBVkK0IyBB2xYxJZV350T/LlDjlZgVae+1elXjctmlLRazzIEC7QtKet/L9ViUXOeA+DfSowFeI/brDXsKnJTUnHw0bmV6WCLAym+1enBXXgFOkJQoYA013629C0t24wamK6qg1HTXD8qL6DR5ETmgWAbVGfusz8rLztEctYf0ytM6mmmViB4k/Of2zAjmyK0zG5734FlxbqkZlSpCffZy8aN8HJy+CgRU3MtanjeUW/TifdBNPuI9l3mNzMHROnwRT6AQL6q5ZWJs9KLq20YJo+BfqbNnH4aIDMjzBO5B/pJV2XUaFNltNFE3Be6hECEJi0xg==";
		try {
			KeyPair keys = keyGen();
			 Cipher cipher = Cipher.getInstance("RSA");
			  cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
			  	byte[] raw = Base64.getDecoder().decode(cypher);
			    decryptedBytes = cipher.doFinal(raw);
			    String decrypted = new String(decryptedBytes);
			    System.out.println(decrypted);
			    RSAPublicKey publicKey = (RSAPublicKey) keys.getPublic();
			    System.out.println("e: " + publicKey.getPublicExponent());
			    System.out.println("mod: " + publicKey.getModulus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}