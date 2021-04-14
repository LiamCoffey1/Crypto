package classical;

public class Key<K, A> {
	K valueKey;
	A[] arrayKey;
	
	public Key(K value, A[] array) {
		this.valueKey = value;
		this.arrayKey = array;
	}
}
