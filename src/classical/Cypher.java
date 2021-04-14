package classical;

public interface Cypher<K, A> {

	public String encrypt(String input, Key<K, A> key );
	
	public String decrypt(String input, Key<K, A> key );
	
	public String crack(String cypher);
}
