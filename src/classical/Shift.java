package classical;


public class Shift implements Cypher<Integer, Object> {

	
	private static String ShiftArray(String in, int shiftBy) {
		char[] buffer = in.toCharArray();
		for(int i = 0; i < buffer.length; i++) {
			int num = (int)buffer[i];
			int nextLetter = num + shiftBy;
			if(buffer[i] != ' ')
				buffer[i] = nextLetter < 'Z' ? (char)(nextLetter) : (char)(nextLetter - 'Z' + 'A');
		}
		return new String(buffer);
	}
	
	@Override
	public String encrypt(String input, Key<Integer, Object> key) {
		return ShiftArray(input, key.valueKey);
	}

	@Override
	public String decrypt(String input, Key<Integer, Object> key) {
		return ShiftArray(input, -key.valueKey);
	}

	@Override
	public String crack(String cypher) {
		char[] buffer = cypher.toCharArray();
		for(int j = 1; j <= 26; j++) {
			System.out.println(ShiftArray(new String(buffer), j));
		
		}
		return null;
	}

}
