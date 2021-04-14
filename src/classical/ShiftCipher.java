package classical;
import java.util.Scanner;

public class ShiftCipher {
	

	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		Key<Integer, Object> encryptionKey = new Key<Integer, Object>(5, null);
		Shift shift = new Shift();
		System.out.println("Original: " + input);
		String encrypted = shift.encrypt(input, encryptionKey);
		System.out.println("Encrypted: " + encrypted);
		String decrypted = shift.decrypt(encrypted, encryptionKey);
		System.out.println("Decrypted: " + decrypted);
		System.out.println("Crack: " + decrypted);
		shift.crack(encrypted);
		sc.close();
		
	}
	
}
