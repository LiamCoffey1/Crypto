package classical;


public class Substitute implements Cypher<String, Object> {

	@Override
	public String encrypt(String input, Key<String, Object> key) {
		StringBuilder sb = new StringBuilder();
		for(char c : input.toCharArray()) {
			sb.append(key.valueKey.charAt((int)c - 'a'));
		}
		return sb.toString();
	}

	@Override
	public String decrypt(String input, Key<String, Object> key) {
		StringBuilder sb = new StringBuilder();
		for(char c : input.toCharArray()) {
			int pos = key.valueKey.indexOf(c);
			sb.append((char)('a' + pos));
		}
		return sb.toString();
	}

	@Override
	public String crack(String cypher) {
		// TODO Auto-generated method stub
		return null;
	}

}
