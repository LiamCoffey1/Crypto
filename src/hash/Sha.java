package hash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha {
	
	/**
	* Convenience method to convert a byte to a hex string.
	*
	* @param data the byte to convert
	* @return String the converted byte
	*/
	public static String byteToHex(byte data)
	{
	StringBuffer buf = new StringBuffer();
	buf.append(toHexChar((data>>>4)&0x0F));
	buf.append(toHexChar(data&0x0F));
	return buf.toString();
	}

	/**
	* Convenience method to convert an int to a hex char.
	*
	* @param i the int to convert
	* @return char the converted char
	*/
	public static char toHexChar(int i)
	{
	if ((0 <= i) && (i <= 9 ))
	return (char)('0' + i);
	else
	return (char)('a' + (i-10));
	}
	
	static String md5(String input) {
        MessageDigest mDigest;
        StringBuffer sb = new StringBuffer();
        
		try {
			mDigest = MessageDigest.getInstance("MD5");
        byte[] result = mDigest.digest(input.getBytes());
        for (int i = 0; i < result.length; i++) {
        	 sb.append(byteToHex(result[i]));
        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
   
        return sb.toString();
    }

	static String sha1(String input) {
        MessageDigest mDigest;
        StringBuffer sb = new StringBuffer();
        
		try {
			mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        for (int i = 0; i < result.length; i++) {
        	 sb.append(byteToHex(result[i]));
        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
   
        return sb.toString();
    }
	
	static String sha2(String input) {
        MessageDigest mDigest;
        StringBuffer sb = new StringBuffer();
        
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        for (int i = 0; i < result.length; i++) {
        	 sb.append(byteToHex(result[i]));
        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
   
        return sb.toString();
    }
	
	static String sha3(String input) {
        MessageDigest mDigest;
        StringBuffer sb = new StringBuffer();
        
		try {
			mDigest = MessageDigest.getInstance("SHA3-384");
        byte[] result = mDigest.digest(input.getBytes());
        for (int i = 0; i < result.length; i++) {
        	 sb.append(byteToHex(result[i]));
        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
   
        return sb.toString();
    }

	public static void main(String args[]) {
		System.out.println(md5("md5"));
		System.out.println(sha1("sha1"));
		System.out.println(sha2("sha1"));
		System.out.println(sha3("sha3"));
	}
}
