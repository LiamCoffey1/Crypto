package classical;

import java.util.Scanner;

public class SubstitutionCypher {

	public static final String key = "phqgiumeaylnofdxjkrcvstzwb";
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		System.out.println("Original: " + input);
		Key<String, Object> encryptionKey = new Key<String, Object>(key, null);
		String encrypted = new Substitute().encrypt(input, encryptionKey);
		System.out.println("Encrypted: " + encrypted);
		String decrypted = new Substitute().decrypt(encrypted, encryptionKey);
		System.out.println("Decrypted: " + decrypted);
		
		sc.close();
	}
	
}