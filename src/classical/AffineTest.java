package classical;

/**
 * @author liamc
 *
 */
public class AffineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Affine aff = new Affine();
		String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
		String ALPHABET2 = "abcdefghijklmnopqrstuvwxyz!£$%^&*_+-=";
		Key<String, Integer> encryptionKey = new Key<String, Integer>(ALPHABET, new Integer[] {5, 8});
		String message = "affinecypher";
		System.out.println(message);
		String encrypted = aff.encrypt(message, encryptionKey);
		System.out.println(encrypted);
		System.out.println(aff.decrypt(encrypted, encryptionKey));
		
		String[] keys = aff.findKeys(encrypted, message, ALPHABET);
		String[] keys2 = aff.findKeys("lpa", "&*t", ALPHABET2);
		String[] keys3 = aff.findKeys("-qn", "csd", ALPHABET2);
		
	
		
		
		System.out.println("A: " + keys[0]);
		System.out.println("B: " + keys[1]);
		System.out.println("A: " + keys2[0]);
		System.out.println("B: " + keys2[1]);
		System.out.println("A: " + keys3[0]);
		System.out.println("B: " + keys3[1]);

	}

}
