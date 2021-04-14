package classical;

import java.util.Scanner;

public class PermutationCypher {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		Key<Integer, Integer> encryptionKey = new Key<Integer, Integer>(5, new Integer[] {4,3,2,1,0});
		System.out.println("Original: " + input);
		String encrypted = new Perm().encrypt(input, encryptionKey);
		System.out.println("Encrypted: " + encrypted);
		String decrypted = new Perm().decrypt(encrypted, encryptionKey);
		System.out.println("Decrypted: " + decrypted);
		
		sc.close();
	}
	
}
