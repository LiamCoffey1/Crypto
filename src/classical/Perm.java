package classical;

public class Perm implements Cypher<Integer, Integer> {

	@Override
	public String encrypt(String input, Key<Integer, Integer> key) {
		char[] array = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		int splitSize = key.valueKey;
		for(int j = 0; j < array.length; j+=splitSize) {
			for(int i = 0; i < splitSize; i++) {
				int indexInSet = j + key.arrayKey[i];
				if(indexInSet >= array.length) // padding
					sb.append('z');
				else sb.append(array[indexInSet]);
			}
		}
		return sb.toString();
		
	}

	@Override
	public String decrypt(String input, Key<Integer, Integer> key) {
		char[] array = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < array.length; j+=key.valueKey) {
			for(int i = 0; i < key.valueKey; i++) {
				sb.append(array[key.arrayKey[i]]);
			}
		}
		return sb.toString();
	}

	@Override
	public String crack(String cypher) {
		// TODO Auto-generated method stub
		return null;
	}

}
