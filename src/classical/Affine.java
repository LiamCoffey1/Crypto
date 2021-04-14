package classical;

public class Affine implements Cypher<String, Integer> {
	

	/*
	 * Encryption : Char -> alphabet(((A * Char) + B) % N)
	 * Where:
	 * Char = index of letter in alphabet
	 * A = Key 1
	 * B = Key 2
	 * N = Length of alphabet
	 * alphabet = input alphabet
	 */
	@Override
	public String encrypt(String input, Key<String, Integer> key) {
		String alphabet = key.valueKey;
		char[] chars = input.toCharArray();
		int A = key.arrayKey[0];
		int B = key.arrayKey[1];
		int N = alphabet.length();
		for(int i = 0; i < chars.length; i++) {
			int num = alphabet.indexOf(chars[i]);
			int newIndex = Math.floorMod(((A*num) + B), N);
			chars[i] = alphabet.charAt(newIndex); 
		}
		return new String(chars);
	}
	
	/*
	 * Decryption : Char -> alphabet(((A^-1 * (Char - B) % N)
	 * Where:
	 * Char = index of char in alphabet
	 * A = Key 1
	 * B = Key 2
	 * N = Length of alphabet
	 * alphabet = input alphabet
	 */
	@Override
	public String decrypt(String input, Key<String, Integer> key) {
		String alphabet = key.valueKey;
		char[] chars = input.toCharArray();
		int A = key.arrayKey[0];
		int B = key.arrayKey[1];
		int N = alphabet.length();
		int AI = ModInverse.calc(A, N);
		for(int i = 0; i < chars.length; i++) {
			char current = chars[i];
			int num = alphabet.indexOf(current);
			int newIndex = (AI * (num - B));
			int mod = Math.floorMod(newIndex, N);
			chars[i] = alphabet.charAt(mod); 
		}
		return new String(chars);
	}
	
	
	/*
	 * Chosen plaintext attack
	 * idea: simultaneous equations on first two chars
	 * of cypher and plaintext
	 */
	public String[] findKeys(String cypher, String plainText, String alphabet) {
		int A1 = alphabet.indexOf(plainText.charAt(0)),
			A2 = alphabet.indexOf(cypher.charAt(0)),
			B1 = alphabet.indexOf(plainText.charAt(1)),
			B2 = alphabet.indexOf(cypher.charAt(1)),
			N = alphabet.length(),
			R = A1 - B1,
			L = A2 - B2;
		int modInv = ModInverse.calc(R, N);
		int ANEW = Math.floorMod(modInv * L, N);
		int B = Math.floorMod(A2 - (A1 * ANEW), N);
		return new String[]{String.valueOf(ANEW), String.valueOf(B)};
	}

	/*
	 * Bruteforce check all A, B and N
	 * A will be coprime to N
	 */
	@Override
	public String crack(String cypher) {
		// TODO Auto-generated method stub
		return null;
	}

}
